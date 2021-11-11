package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Bed;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BedDao {
    void insertBed(UUID id, Bed bed);

    List<Bed> selectAllBeds();

    void deleteBedById(UUID id);

    void updateBedById(UUID id, Bed bed);

    Optional<Bed> selectBedById(UUID id);
}
