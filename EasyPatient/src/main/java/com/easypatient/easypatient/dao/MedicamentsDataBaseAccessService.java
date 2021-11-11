package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Medicaments;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MedicamentsPostgres")
public class MedicamentsDataBaseAccessService implements MedicamentsDao{
    @Override
    public void insertMedicaments(UUID id, Medicaments medicaments) {
    }

    @Override
    public List<Medicaments> selectAllMedicamentss() {
        return List.of();
    }

    @Override
    public void deleteMedicamentsById(UUID id) {
    }

    @Override
    public void updateMedicamentsById(UUID id, Medicaments medicaments) {
    }

    @Override
    public Optional<Medicaments> selectMedicamentsById(UUID id) {
        return Optional.ofNullable(Medicaments.builder().build());
    }
}
