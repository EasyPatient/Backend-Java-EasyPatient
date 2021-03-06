package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.dto.SmartbandDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;
import com.easypatient.easypatient.dto.SmartbandGetDTO;
import com.easypatient.easypatient.model.SmartbandData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("SmartbandPostgres")
public class SmartbandDataBaseAccessService implements SmartbandDao {

    final String sqlSelectAllSmartband = "SELECT id, mac, name, created_at, updated_at FROM smartband";
    final String sqlSelectSmartbandByID = "SELECT id, mac, name, created_at, updated_at FROM smartband WHERE id = ?";
    final String sqlInsertSmartband = "INSERT INTO smartband VALUES(?, ?, ?, ?)";
    final String sqlSelectSmartbandByVariable = "SELECT id, mac, name, created_at, updated_at FROM smartband WHERE";
    final String sqlMac = "(mac = ?)";
    final String sqlName = "(name = ?)";
    final String sqlCreatedAfter = "(created_at >= ?)";
    final String sqlUpdatedAfter = "(updated_at >= ?)";
    final String sqlAnd = "AND";
    final String sqlSemicolon = ";";
    final String sqlDeleteSmartband = "DELETE FROM smartband WHERE id = ?";
    final String sqlUpdateSmartbandById = "UPDATE smartband SET mac = ?, name = ?, updated_at = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SmartbandDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static SmartbandGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String mac = resultSet.getString("mac");
        String name = resultSet.getString("name");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return SmartbandGetDTO.builder()
                .id(id)
                .mac(mac)
                .name(name)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertSmartband(SmartbandDTO smartband) {
        LocalDateTime date = LocalDateTime.now();

        jdbcTemplate.update(sqlInsertSmartband,
                smartband.getMac(),
                smartband.getName(),
                date,
                date);
    }

    @Override
    public List<SmartbandGetDTO> selectAllSmartbands() {
        return jdbcTemplate.query(sqlSelectAllSmartband,
                SmartbandDataBaseAccessService::mapRow);
    }

    @Override
    public void deleteSmartbandById(UUID smartbandId) {
        Object[] args = new Object[]{smartbandId};
        jdbcTemplate.update(sqlDeleteSmartband, args);
    }

    @Override
    public void updateSmartbandById(UUID id,
                                    Optional<String> mac,
                                    Optional<String> name) throws SQLException {
        Optional<SmartbandGetDTO> existingSmartbandOptional = selectSmartbandById(id);
        SmartbandGetDTO existingSmartband;
        String macToUpdate;
        String nameToUpdate;
        LocalDateTime updatedAtToUpdate;

        if(existingSmartbandOptional.isPresent()) {
            existingSmartband = existingSmartbandOptional.get();
        } else {
            throw new SQLException("Smartband with ID: " + id + "doesn't exist.");
        }

        macToUpdate = mac.orElseGet(existingSmartband::getMac);
        nameToUpdate = name.orElseGet(existingSmartband::getName);
        updatedAtToUpdate = LocalDateTime.now();

        jdbcTemplate.update(sqlUpdateSmartbandById,
                macToUpdate,
                nameToUpdate,
                updatedAtToUpdate,
                id);
    }

    @Override
    public Optional<SmartbandGetDTO> selectSmartbandById(UUID id) {
        SmartbandGetDTO smartband = jdbcTemplate.queryForObject(
                sqlSelectSmartbandByID,
                new Object[]{id},
                SmartbandDataBaseAccessService::mapRow);
        return Optional.ofNullable(smartband);
    }

    @Override
    public List<SmartbandGetDTO> selectSmartbandByVariables(Optional<String> mac,
                                                            Optional<String> name,
                                                            Optional<LocalDateTime> createdAfter,
                                                            Optional<LocalDateTime> updatedAfter) throws SQLException {
        int i = 0;
        int k = 0;

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectSmartbandByVariable));

        if(mac.isPresent()) {
            i++;
            expressions.add(sqlMac);
        }
        if(name.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlName);
        }
        if(createdAfter.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlCreatedAfter);
        }
        if(updatedAfter.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlUpdatedAfter);
        }

        String sqlExpression = String.join(" ", expressions);
        sqlExpression = sqlExpression.concat(sqlSemicolon);

        if(i != 0) {
            Object[] jdbcTable = new Object[i];
            if(mac.isPresent()) {
                jdbcTable[k] = mac.get();
                k++;
            }
            if(name.isPresent()) {
                jdbcTable[k] = name.get();
                k++;
            }
            if(createdAfter.isPresent()) {
                jdbcTable[k] = createdAfter.get();
                k++;
            }
            if(updatedAfter.isPresent()) {
                jdbcTable[k] = updatedAfter.get();
                k++;
            }
            return jdbcTemplate.query(
                    sqlExpression,
                    jdbcTable,
                    SmartbandDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }
    }
}
