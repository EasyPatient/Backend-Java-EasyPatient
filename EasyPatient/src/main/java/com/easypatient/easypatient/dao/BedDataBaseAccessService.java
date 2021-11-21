package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.BedDTO;
import com.easypatient.easypatient.dto.BedGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("BedPostgres")
public class BedDataBaseAccessService implements BedDao {

    @Override
    public void insertBed(BedDTO bed) {
    }

    @Override
    public List<BedGetDTO> selectAllBeds() {
        return List.of();
    }

    @Override
    public void deleteBedById(UUID id) {
    }

    @Override
    public void updateBedById(UUID id,
                              Optional<Integer> number,
                              Optional<UUID> patientId,
                              Optional<UUID> roomId) throws SQLException {
    }

    @Override
    public Optional<BedGetDTO> selectBedById(UUID id) {
        return Optional.ofNullable(BedGetDTO.builder().build());
    }

    @Override
    public List<BedGetDTO> selectBedByVariables(Optional<Integer> number,
                                                Optional<UUID> patientId,
                                                Optional<UUID> roomId,
                                                Optional<LocalDateTime> updatedAt,
                                                Optional<LocalDateTime> createdAt) throws SQLException {
        return List.of();
    }
}
