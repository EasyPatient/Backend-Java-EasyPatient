package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.SmartbandDataDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SmartbandDataDao {
    void insertSmartbandData(SmartbandDataDTO smartbandData);

    List<SmartbandDataGetDTO> selectAllSmartbandDatas();

    void deleteSmartbandDataByIds(UUID smartbandId, UUID patientId);

    List<SmartbandDataGetDTO> selectSmartbandDataByPatientId(UUID patientId) throws SQLException;

    List<SmartbandDataGetDTO> selectSmartbandDataBySmartbandId(UUID smartbandId) throws SQLException;

    List<SmartbandDataGetDTO> selectSmartbandDataByVariable(Optional<UUID> smartbandId,
                                                             Optional<UUID> patientId,
                                                             Optional<String> heartRate,
                                                             Optional<String> oxygen,
                                                             Optional<String> temperature,
                                                             Optional<String> battery,
                                                             Optional<LocalDateTime> createdAt,
                                                             Optional<LocalDateTime> updatedAt) throws SQLException;
}
