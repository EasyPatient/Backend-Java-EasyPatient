package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.SmartbandDataDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface SmartbandDataDao {
    void insertSmartbandData(SmartbandDataDTO smartbandData);

    List<SmartbandDataGetDTO> selectAllSmartbandDatas();

    void deleteSmartbandDataByIds(UUID smartbandId, UUID patientId);

    List<SmartbandDataGetDTO> selectSmartbandDataByPatientId(UUID patientId) throws SQLException;

    List<SmartbandDataGetDTO> selectSmartbandDataBySmartbandId(UUID smartbandId) throws SQLException;
}
