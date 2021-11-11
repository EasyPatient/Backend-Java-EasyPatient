package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientDao {
    void insertPatient(UUID id, Patient patient);

    List<Patient> selectAllPeople();

    void deletePatientById(UUID id);

    void updatePatientById(UUID id, Patient patient);

    Optional<Patient> selectPatientById(UUID id);
}
