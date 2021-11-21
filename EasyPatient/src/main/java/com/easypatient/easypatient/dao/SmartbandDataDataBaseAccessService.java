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
import java.util.Optional;
import java.util.UUID;

@Repository("SmartbandDataPostgres")
public class SmartbandDataDataBaseAccessService implements SmartbandDataDao {

    final String sqlSelectAllSmartbandDatas = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data";
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
        LocalDateTime date = LocalDateTime.now();

        jdbcTemplate.update(sqlInsertSmartbandData,
                smartbandData.getSmartbandId(),
                smartbandData.getPatientId(),
                smartbandData.getHeartRate(),
                smartbandData.getOxygen(),
                smartbandData.getTemperature(),
                smartbandData.getBattery(),
                date,
                date);
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
        return List.of();
    }

    @Override
    public List<SmartbandDataGetDTO> selectSmartbandDataBySmartbandId(UUID smartbandId) throws SQLException {
        return List.of();
    }

    @Override
    public List<SmartbandDataGetDTO> selectSmartbandDataByVariable(Optional<UUID> smartbandId,
                                                                   Optional<UUID> patientId,
                                                                   Optional<String> heartRate,
                                                                   Optional<String> oxygen,
                                                                   Optional<String> temperature,
                                                                   Optional<String> battery,
                                                                   Optional<LocalDateTime> createdAt,
                                                                   Optional<LocalDateTime> updatedAt) throws SQLException {
        int i = 0;
        int k = 0;
        final String sqlSelectSmartbandDataByVariable = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data WHERE ";
        final String sqlSmartbandId = " (smartband_id = ?)";
        final String sqlPatientId = " (patient_id = ?)";
        final String sqlHeartRate = " (heart_rate = ?)";
        final String sqlOxygen = " (oxygen = ?)";
        final String sqlTemperature = " (temperature = ?)";
        final String sqlBattery = " (battery = ?)";
        final String sqlCreatedAt = " (created_at = ?)";
        final String sqlUpdatedAt = " (updated_at = ?)";
        final String sqlAnd = " AND ";
        final String sqlSemicolon = ";";

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectSmartbandDataByVariable));

        if(smartbandId.isPresent()) {
            i++;
            expressions.add(sqlSmartbandId);
        }
        if(patientId.isPresent()) {
            i++;
            expressions.add(sqlPatientId);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(heartRate.isPresent()) {
            i++;
            expressions.add(sqlHeartRate);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(oxygen.isPresent()) {
            i++;
            expressions.add(sqlOxygen);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(temperature.isPresent()) {
            i++;
            expressions.add(sqlTemperature);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(battery.isPresent()) {
            i++;
            expressions.add(sqlBattery);
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
            if(smartbandId.isPresent()) {
                jdbcTable[k] = smartbandId.get();
                k++;
            }
            if(patientId.isPresent()) {
                jdbcTable[k] = patientId.get();
                k++;
            }
            if(heartRate.isPresent()) {
                jdbcTable[k] = heartRate.get();
                k++;
            }
            if(oxygen.isPresent()) {
                jdbcTable[k] = oxygen.get();
                k++;
            }
            if(temperature.isPresent()) {
                jdbcTable[k] = temperature.get();
                k++;
            }
            if(battery.isPresent()) {
                jdbcTable[k] = battery.get();
                k++;
            }
            if(createdAt.isPresent()) {
                jdbcTable[k] = createdAt.get();
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
                    SmartbandDataDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }
    }

}
