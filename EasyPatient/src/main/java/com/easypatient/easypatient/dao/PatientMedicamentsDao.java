package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.PatientMedicaments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientMedicamentsDao {
    void insertPatientMedicaments(UUID id, PatientMedicaments patientMedicaments);

    List<PatientMedicaments> selectAllPatientMedicaments();

    void deletePatientMedicamentsById(UUID id);

    void updatePatientMedicamentsById(UUID id, PatientMedicaments patientMedicaments);

    Optional<PatientMedicaments> selectPatientMedicamentsById(UUID id);
}
