package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.PatientMedicamentsDao;
import com.easypatient.easypatient.dto.PatientMedicamentsDTO;
import com.easypatient.easypatient.dto.PatientMedicamentsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class PatientMedicamentsService {
    private final PatientMedicamentsDao patientMedicamentsDao;

    @Autowired
    public PatientMedicamentsService(@Qualifier("PatientMedicamentsPostgres") PatientMedicamentsDao patientMedicamentsDao) {
        this.patientMedicamentsDao = patientMedicamentsDao;
    }

    public void addPatientMedicaments(PatientMedicamentsDTO patientMedicaments) {
        patientMedicamentsDao.insertPatientMedicaments(patientMedicaments);
    }

    public List<PatientMedicamentsGetDTO> getAllPatientMedicaments() {
        return patientMedicamentsDao.selectAllPatientMedicaments();
    }

    public List<PatientMedicamentsGetDTO> getPatientMedicamentsByPatientId(UUID patientId) throws SQLException {
        return patientMedicamentsDao.selectPatientMedicamentsByPatientId(patientId);
    }

    public List<PatientMedicamentsGetDTO> getPatientMedicamentsByMedicamentsId(UUID medicamentsId) throws SQLException {
        return patientMedicamentsDao.selectPatientMedicamentsByMedicamentsId(medicamentsId);
    }

    public void deletePatientMedicaments(UUID patientId, UUID medicamentsId) {
        patientMedicamentsDao.deletePatientMedicamentsByIds(patientId, medicamentsId);
    }

}
