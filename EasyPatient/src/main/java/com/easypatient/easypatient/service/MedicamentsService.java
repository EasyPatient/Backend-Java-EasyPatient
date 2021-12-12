package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.MedicamentsDao;
import com.easypatient.easypatient.dto.MedicamentsDTO;
import com.easypatient.easypatient.dto.MedicamentsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicamentsService {
    private final MedicamentsDao medicamentsDao;

    @Autowired
    public MedicamentsService(@Qualifier("MedicamentsPostgres") MedicamentsDao medicamentsDao) {
        this.medicamentsDao = medicamentsDao;
    }

    public void addMedicaments(MedicamentsDTO medicaments) {
        medicamentsDao.insertMedicaments(medicaments);
    }

    public List<MedicamentsGetDTO> getAllMedicaments() {
        return medicamentsDao.selectAllMedicaments();
    }

    public Optional<MedicamentsGetDTO> getMedicamentsById(UUID id) {
        return medicamentsDao.selectMedicamentsById(id);
    }

    public List<MedicamentsGetDTO> getMedicamentsByVariables(Optional<String> name,
                                                             Optional<String> type,
                                                             Optional<String> value,
                                                             Optional<LocalDateTime> createdAfter,
                                                             Optional<LocalDateTime> updatedAfter) throws SQLException {
        return medicamentsDao.selectMedicamentsByVariables(name, type, value, createdAfter, updatedAfter);
    }

    public void deleteMedicaments(UUID id) {
        medicamentsDao.deleteMedicamentsById(id);
    }

    public void updateMedicaments(UUID id,
                                  Optional<String> name,
                                  Optional<String> type,
                                  Optional<String> value) throws SQLException {
        medicamentsDao.updateMedicamentsById(id, name, type, value);
    }
}
