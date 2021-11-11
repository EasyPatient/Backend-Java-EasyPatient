package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.MedicamentsDao;
import com.easypatient.easypatient.model.Medicaments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MedicamentsService {
    private final MedicamentsDao medicamentsDao;

    @Autowired
    public MedicamentsService(@Qualifier("MedicamentsPostgres") MedicamentsDao medicamentsDao) {
        this.medicamentsDao = medicamentsDao;
    }

    public void addMedicaments(Medicaments medicaments) {
        medicamentsDao.insertMedicaments(medicaments.getId(), medicaments);
    }

    public List<Medicaments> getAllMedicamentss() {
        return medicamentsDao.selectAllMedicamentss();
    }

    public Optional<Medicaments> getMedicamentsById(UUID id) {
        return medicamentsDao.selectMedicamentsById(id);
    }

    public void deleteMedicaments(UUID id) {
        medicamentsDao.deleteMedicamentsById(id);
    }

    public void updateMedicaments(UUID id, Medicaments medicaments) {
        medicamentsDao.updateMedicamentsById(id, medicaments);
    }
}
