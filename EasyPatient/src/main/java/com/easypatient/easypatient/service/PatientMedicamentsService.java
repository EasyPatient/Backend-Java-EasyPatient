package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.PatientMedicamentsDao;
import com.easypatient.easypatient.model.PatientMedicaments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PatientMedicamentsService {
    private final PatientMedicamentsDao patientMedicamentsDao;

    @Autowired
    public PatientMedicamentsService(@Qualifier("PatientMedicamentsPostgres") PatientMedicamentsDao patientMedicamentsDao) {
        this.patientMedicamentsDao = patientMedicamentsDao;
    }

    public void addPatientMedicaments(PatientMedicaments patientMedicaments) {
        patientMedicamentsDao.insertPatientMedicaments(patientMedicaments.getPatientId(), patientMedicaments);
    }

    public List<PatientMedicaments> getAllPatientMedicamentss() {
        return patientMedicamentsDao.selectAllPatientMedicaments();
    }

    public Optional<PatientMedicaments> getPatientMedicamentsById(UUID patientId) {
        return patientMedicamentsDao.selectPatientMedicamentsById(patientId);
    }

    public void deletePatientMedicaments(UUID patientId) {
        patientMedicamentsDao.deletePatientMedicamentsById(patientId);
    }

    public void updatePatientMedicaments(UUID patientId, PatientMedicaments patientMedicaments) {
        patientMedicamentsDao.updatePatientMedicamentsById(patientId, patientMedicaments);
    }
}
