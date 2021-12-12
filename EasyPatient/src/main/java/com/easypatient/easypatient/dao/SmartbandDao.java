package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.SmartbandDTO;
import com.easypatient.easypatient.dto.SmartbandGetDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SmartbandDao {
    void insertSmartband(SmartbandDTO smartband);

    List<SmartbandGetDTO> selectAllSmartbands();

    void deleteSmartbandById(UUID smartbandId);

    void updateSmartbandById(UUID id,
                             Optional<String> mac,
                             Optional<String> name) throws SQLException;

    Optional<SmartbandGetDTO> selectSmartbandById(UUID smartbandId);

    List<SmartbandGetDTO> selectSmartbandByVariables(Optional<String> mac,
                                                     Optional<String> name,
                                                     Optional<LocalDateTime> createdAfter,
                                                     Optional<LocalDateTime> updatedAfter) throws SQLException;
}
