package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.PatientDao;
import com.easypatient.easypatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientDao patientDao;

    @Autowired
    public PatientService(@Qualifier("PatientPostgres") PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public void addPatient(Patient patient) {
        patientDao.insertPatient(patient.getId(), patient);
    }

    public List<Patient> getAllPeople() {
        return patientDao.selectAllPeople();
    }

    public Optional<Patient> getPatientById(UUID id) {
        return patientDao.selectPatientById(id);
    }

    public void deletePatient(UUID id) {
        patientDao.deletePatientById(id);
    }

    public void updatePatient(UUID id, Patient patient) {
        patientDao.updatePatientById(id, patient);
    }
}
