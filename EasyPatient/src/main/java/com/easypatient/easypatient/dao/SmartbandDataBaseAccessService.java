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
    }

    @Override
    public List<SmartbandGetDTO> selectAllSmartbands() {
        return jdbcTemplate.query(sqlSelectAllSmartband,
                SmartbandDataBaseAccessService::mapRow);
    }

    @Override
    public void deleteSmartbandById(UUID smartbandId) {
    }

    @Override
    public void updateSmartbandById(UUID id,
                                    Optional<String> mac,
                                    Optional<String> name) throws SQLException {
    }

    @Override
    public Optional<SmartbandGetDTO> selectSmartbandById(UUID id) {
        return Optional.ofNullable(SmartbandGetDTO.builder().build());
    }

    @Override
    public List<SmartbandGetDTO> selectSmartbandByVariables(Optional<String> mac,
                                                            Optional<String> name,
                                                            Optional<LocalDateTime> createdAt,
                                                            Optional<LocalDateTime> updatedAt) throws SQLException {
        int i = 0;
        int k = 0;
        final String sqlSelectSmartbandByVariable = "SELECT mac, name, created_at, updated_at FROM smartband WHERE ";
        final String sqlMac = " (mac = ?)";
        final String sqlName = " (name = ?)";
        final String sqlCreatedAt = " (created_at = ?)";
        final String sqlUpdatedAt = " (updated_at = ?)";
        final String sqlAnd = " AND ";
        final String sqlSemicolon = ";";

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectSmartbandByVariable));

        if(mac.isPresent()) {
            i++;
            expressions.add(sqlMac);
        }
        if(name.isPresent()) {
            i++;
            expressions.add(sqlName);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(createdAt.isPresent()) {
            i++;
            expressions.add(sqlCreatedAt);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(updatedAt.isPresent()) {
            i++;
            expressions.add(sqlUpdatedAt);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }

        expressions.add(sqlSemicolon);
        String sqlExpression = String.join(" ", expressions);

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
            if(createdAt.isPresent()) {
                jdbcTable[k] = createdAt.get();
                k++;
            }
            if(updatedAt.isPresent()) {
                jdbcTable[k] = updatedAt.get();
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
