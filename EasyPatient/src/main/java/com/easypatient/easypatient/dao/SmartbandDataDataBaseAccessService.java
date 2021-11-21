package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.SmartbandDataDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository("SmartbandDataPostgres")
public class SmartbandDataDataBaseAccessService implements SmartbandDataDao {
    @Override
    public void insertSmartbandData(SmartbandDataDTO smartbandData) {
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
