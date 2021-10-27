package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Bed;
import com.easypatient.easypatient.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class BedDataBaseAccessService implements BedDao {
    private static List<Bed> DB = new ArrayList<>();

    @Override
    public int insertBed(UUID id, Bed bed) {
        DB.add(new Bed(id, bed.getNumber(), bed.getPatientId(), bed.getRoomId()));
        return 1;
    }

    @Override
    public List<Bed> selectAllBeds() {
        return DB;
    }

    @Override
    public int deleteBedById(UUID id) {
        Optional<Bed> bedToDelete = selectBedById(id);
        if (bedToDelete.isEmpty()) {
            return 0;
        }
        DB.remove(bedToDelete.get());
        return 1;
    }

    @Override
    public int updateBedById(UUID id, Bed bed) {
        return selectBedById(id)
                .map(p -> {
                    int indexOfBedToUpdate = DB.indexOf(p);
                    if (indexOfBedToUpdate >= 0) {
                        DB.set(indexOfBedToUpdate, new Bed(id,
                                bed.getNumber(),
                                bed.getPatientId(),
                                bed.getRoomId()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public Optional<Bed> selectBedById(UUID id) {
        return DB.stream()
                .filter(bed -> bed.getId().equals(id))
                .findFirst();
    }
}
