package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Bed;
import com.easypatient.easypatient.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("BedPostgres")
public class BedDataBaseAccessService implements BedDao {

    @Override
    public int insertBed(UUID id, Bed bed) {
        return 1;
    }

    @Override
    public List<Bed> selectAllBeds() {
        return List.of();
    }

    @Override
    public int deleteBedById(UUID id) {
        return 1;
    }

    @Override
    public int updateBedById(UUID id, Bed bed) {
        return 1;
    }

    @Override
    public Optional<Bed> selectBedById(UUID id) {
        return Optional.ofNullable(Bed.builder().build());
    }
}
