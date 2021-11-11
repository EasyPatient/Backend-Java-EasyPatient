package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.PatientMedicaments;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PatientMedicamentsPostgres")
public class PatientMedicamentsDataBaseAccessService implements PatientMedicamentsDao {
    @Override
    public void insertPatientMedicaments(UUID patientId, PatientMedicaments patientMedicaments) {
    }

    @Override
    public List<PatientMedicaments> selectAllPatientMedicaments() {
        return List.of();
    }

    @Override
    public void deletePatientMedicamentsById(UUID patientId) {
    }

    @Override
    public void updatePatientMedicamentsById(UUID patientId, PatientMedicaments patientMedicaments) {
    }

    @Override
    public Optional<PatientMedicaments> selectPatientMedicamentsById(UUID patientId) {
        return Optional.ofNullable(PatientMedicaments.builder().build());
    }
}
