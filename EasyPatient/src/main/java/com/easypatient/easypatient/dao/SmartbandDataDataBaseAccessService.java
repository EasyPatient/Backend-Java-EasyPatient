package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.SmartbandDataDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;
import com.easypatient.easypatient.model.Smartband;
import com.easypatient.easypatient.model.SmartbandData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository("SmartbandDataPostgres")
public class SmartbandDataDataBaseAccessService implements SmartbandDataDao {

    final String sqlInsertSmartbandData = "INSERT INTO smartband_data VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SmartbandDataDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        return List.of();
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

}
