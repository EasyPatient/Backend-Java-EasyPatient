package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.SmartbandDataDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;
import com.easypatient.easypatient.dto.SmartbandGetDTO;
import com.easypatient.easypatient.model.Smartband;
import com.easypatient.easypatient.model.SmartbandData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository("SmartbandDataPostgres")
public class SmartbandDataDataBaseAccessService implements SmartbandDataDao {

    final String sqlSelectAllSmartbandDatas = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data";
    final String sqlSelectSmartbandDataByPatientId = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data WHERE patient_id = ?";
    final String sqlSelectSmartbandDataBySmartbandId = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data WHERE smartband_id = ?";
    final String sqlInsertSmartbandData = "INSERT INTO smartband_data VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SmartbandDataDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static SmartbandDataGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID smartband_id = UUID.fromString(resultSet.getString("smartband_id"));
        UUID patient_id = UUID.fromString(resultSet.getString("patient_id"));
        String heart_rate = resultSet.getString("heart_rate");
        String oxygen = resultSet.getString("oxygen");
        String temperature = resultSet.getString("temperature");
        String battery = resultSet.getString("battery");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return SmartbandDataGetDTO.builder()
                .smartbandId(smartband_id)
                .patientId(patient_id)
                .heartRate(heart_rate)
                .oxygen(oxygen)
                .temperature(temperature)
                .battery(battery)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertSmartbandData(SmartbandDataDTO smartbandData) {

    }

    @Override
    public List<SmartbandDataGetDTO> selectAllSmartbandDatas() {
        return jdbcTemplate.query(sqlSelectAllSmartbandDatas,
                SmartbandDataDataBaseAccessService::mapRow);
    }

    @Override
    public void deleteSmartbandDataByIds(UUID smartbandId, UUID patientId) {
    }

    @Override
    public List<SmartbandDataGetDTO> selectSmartbandDataByPatientId(UUID patientId) throws SQLException {
        SmartbandDataGetDTO smartbandData = jdbcTemplate.queryForObject(
                sqlSelectSmartbandDataByPatientId,
                new Object[]{patientId},
                SmartbandDataDataBaseAccessService::mapRow);
        return List.of();
    }

    @Override
    public List<SmartbandDataGetDTO> selectSmartbandDataBySmartbandId(UUID smartbandId) throws SQLException {
        SmartbandDataGetDTO smartbandData = jdbcTemplate.queryForObject(
                sqlSelectSmartbandDataBySmartbandId,
                new Object[]{smartbandId},
                SmartbandDataDataBaseAccessService::mapRow);
        return List.of();
    }

}
