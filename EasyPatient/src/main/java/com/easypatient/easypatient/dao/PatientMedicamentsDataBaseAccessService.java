package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientMedicamentsDTO;
import com.easypatient.easypatient.dto.PatientMedicamentsGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository("PatientMedicamentsPostgres")
public class PatientMedicamentsDataBaseAccessService implements PatientMedicamentsDao {
    @Override
    public void insertPatientMedicaments(PatientMedicamentsDTO patientMedicaments) {
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectAllPatientMedicaments() {
        return List.of();
    }

    @Override
    public void deletePatientMedicamentsByIds(UUID patientId, UUID medicamentsId) {
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectPatientMedicamentsByPatientId(UUID patientId) throws SQLException {
        return List.of();
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectPatientMedicamentsByMedicamentsId(UUID medicamentsId) throws SQLException {
        return List.of();
    }
}
