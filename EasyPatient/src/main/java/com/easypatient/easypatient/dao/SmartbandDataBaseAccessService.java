package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.SmartbandDTO;
import com.easypatient.easypatient.dto.SmartbandGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("SmartbandPostgres")
public class SmartbandDataBaseAccessService implements SmartbandDao {
    @Override
    public void insertSmartband(SmartbandDTO smartband) {
    }

    @Override
    public List<SmartbandGetDTO> selectAllSmartbands() {
        return List.of();
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
        return List.of();
    }
}
