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
    }

    @Override
    public void updateSmartbandById(UUID id,
                                    Optional<String> mac,
                                    Optional<String> name) throws SQLException {
    }

    @Override
    public Optional<SmartbandGetDTO> selectSmartbandById(UUID id) {
        SmartbandGetDTO smartband = jdbcTemplate.queryForObject(
                sqlSelectSmartbandByID,
                new Object[]{id},
                SmartbandDataBaseAccessService::mapRow);
        return Optional.ofNullable(SmartbandGetDTO.builder().build());
    }

    @Override
    public List<SmartbandGetDTO> selectSmartbandByVariables(Optional<String> mac,
                                                            Optional<String> name,
                                                            Optional<LocalDateTime> createdAt,
                                                            Optional<LocalDateTime> updatedAt) throws SQLException {
        return List.of();
    }
}
