package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Bed;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("BedPostgres")
public class BedDataBaseAccessService implements BedDao {

    @Override
    public void insertBed(UUID id, Bed bed) {
    }

    @Override
    public List<Bed> selectAllBeds() {
        return List.of();
    }

    @Override
    public void deleteBedById(UUID id) {
    }

    @Override
    public void updateBedById(UUID id, Bed bed) {
    }

    @Override
    public Optional<Bed> selectBedById(UUID id) {
        return Optional.ofNullable(Bed.builder().build());
    }
}
