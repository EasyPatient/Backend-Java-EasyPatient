package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.BedDTO;
import com.easypatient.easypatient.dto.BedGetDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BedDao {

    void insertBed(BedDTO bed) throws SQLException;

    List<BedGetDTO> selectAllBeds();

    void deleteBedById(UUID id);

    void updateBedById(UUID id,
                       Optional<Integer> number,
                       Optional<UUID> roomId) throws SQLException;

    Optional<BedGetDTO> selectBedById(UUID id);

    List<BedGetDTO> selectBedByVariables(Optional<Integer> number,
                                         Optional<UUID> roomId,
                                         Optional<LocalDateTime> updatedAt,
                                         Optional<LocalDateTime> createdAt) throws SQLException;
}
