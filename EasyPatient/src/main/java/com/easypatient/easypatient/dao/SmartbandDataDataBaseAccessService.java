package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.*;
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
    final String sqlSelectSmartbandDataByPatientId = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data WHERE patient_id = ?";
    final String sqlSelectSmartbandDataBySmartbandId = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data WHERE smartband_id = ?";
    final String sqlInsertSmartbandData = "INSERT INTO smartband_data VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    final String sqlSelectSmartbandDataByVariable = "SELECT smartband_id, patient_id, heart_rate, oxygen, temperature, battery, created_at, updated_at FROM smartband_data WHERE";
    final String sqlSmartbandId = "(smartband_id = ?)";
    final String sqlPatientId = "(patient_id = ?)";
    final String sqlHeartRate = "(heart_rate = ?)";
    final String sqlOxygen = "(oxygen = ?)";
    final String sqlTemperature = "(temperature = ?)";
    final String sqlBattery = "(battery = ?)";
    final String sqlCreatedAfter = "(created_at >= ?)";
    final String sqlUpdatedAfter = "(updated_at >= ?)";
    final String sqlAnd = "AND";
    final String sqlSemicolon = ";";
    final String sqlDeleteSmartbandData = "DELETE FROM smartband_data WHERE smartband_id = ? AND (patient_id = ?)";

    private final JdbcTemplate jdbcTemplate;
    private final PatientDataBaseAccessService patientDataBaseAccessService;
    private final SmartbandDataBaseAccessService smartbandDataBaseAccessService;

    @Autowired
    public SmartbandDataDataBaseAccessService(JdbcTemplate jdbcTemplate,
                                              PatientDataBaseAccessService patientDataBaseAccessService,
                                              SmartbandDataBaseAccessService smartbandDataBaseAccessService
                                              ) {
        this.jdbcTemplate = jdbcTemplate;
        this.patientDataBaseAccessService = patientDataBaseAccessService;
        this.smartbandDataBaseAccessService = smartbandDataBaseAccessService;
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
    public void insertSmartbandData(SmartbandDataDTO smartbandData) throws SQLException {
        LocalDateTime date = LocalDateTime.now();

        UUID patientId = smartbandData.getPatientId();
        UUID smartbandId = smartbandData.getSmartbandId();

        Optional<PatientGetDTO> patientFromDB = patientDataBaseAccessService.selectPatientById(patientId);
        Optional<SmartbandGetDTO> smartbandFromDB = smartbandDataBaseAccessService.selectSmartbandById(smartbandId);

        if(patientFromDB.isPresent() && smartbandFromDB.isPresent()) {
            jdbcTemplate.update(sqlInsertSmartbandData,
                    smartbandData.getSmartbandId(),
                    smartbandData.getPatientId(),
                    smartbandData.getHeartRate(),
                    smartbandData.getOxygen(),
                    smartbandData.getTemperature(),
                    smartbandData.getBattery(),
                    date,
                    date);
        } else {
            throw new SQLException("can not insert smartband data with patient ID: " + patientId + ", and smartband ID: " + smartbandId);
        }
    }

    @Override
    public List<SmartbandDataGetDTO> selectAllSmartbandDatas() {
        return jdbcTemplate.query(sqlSelectAllSmartbandDatas,
                SmartbandDataDataBaseAccessService::mapRow);
    }

    @Override
    public void deleteSmartbandDataByIds(UUID smartbandId, UUID patientId) {
        Object[] args = new Object[]{smartbandId, patientId};
        jdbcTemplate.update(sqlDeleteSmartbandData, args);
    }

    @Override
    public List<SmartbandDataGetDTO> selectSmartbandDataByPatientId(UUID patientId) {
        return jdbcTemplate.query(
                sqlSelectSmartbandDataByPatientId,
                new Object[]{patientId},
                SmartbandDataDataBaseAccessService::mapRow);
    }

    @Override
    public List<SmartbandDataGetDTO> selectSmartbandDataBySmartbandId(UUID smartbandId) {
        return jdbcTemplate.query(
                sqlSelectSmartbandDataBySmartbandId,
                new Object[]{smartbandId},
                SmartbandDataDataBaseAccessService::mapRow);
    }

    @Override
    public List<SmartbandDataGetDTO> selectSmartbandDataByVariable(Optional<UUID> smartbandId,
                                                                   Optional<UUID> patientId,
                                                                   Optional<String> heartRate,
                                                                   Optional<String> oxygen,
                                                                   Optional<String> temperature,
                                                                   Optional<String> battery,
                                                                   Optional<LocalDateTime> createdAfter,
                                                                   Optional<LocalDateTime> updatedAfter) throws SQLException {
        int i = 0;
        int k = 0;

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectSmartbandDataByVariable));

        if(smartbandId.isPresent()) {
            i++;
            expressions.add(sqlSmartbandId);
        }
        if(patientId.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlPatientId);
        }
        if(heartRate.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlHeartRate);
        }
        if(oxygen.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlOxygen);
        }
        if(temperature.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlTemperature);
        }
        if(battery.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlBattery);
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
            if(createdAfter.isPresent()) {
                jdbcTable[k] = createdAfter.get();
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
                    SmartbandDataDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }
    }

}
