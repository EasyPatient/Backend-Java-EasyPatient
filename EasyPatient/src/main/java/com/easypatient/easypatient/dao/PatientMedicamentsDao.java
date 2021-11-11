package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.PatientMedicaments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientMedicamentsDao {
    void insertPatientMedicaments(UUID patientId, PatientMedicaments patientMedicaments);

    List<PatientMedicaments> selectAllPatientMedicaments();

    void deletePatientMedicamentsById(UUID patientId);

    void updatePatientMedicamentsById(UUID patientId, PatientMedicaments patientMedicaments);

    Optional<PatientMedicaments> selectPatientMedicamentsById(UUID patientId);
}
