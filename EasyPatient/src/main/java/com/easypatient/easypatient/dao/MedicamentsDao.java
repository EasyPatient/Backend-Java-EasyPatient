package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Medicaments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicamentsDao {
    void insertMedicaments(UUID id, Medicaments medicaments);

    List<Medicaments> selectAllMedicamentss();

    void deleteMedicamentsById(UUID id);

    void updateMedicamentsById(UUID id, Medicaments medicaments);

    Optional<Medicaments> selectMedicamentsById(UUID id);
}
