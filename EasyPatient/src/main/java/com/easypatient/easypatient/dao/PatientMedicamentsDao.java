package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientMedicamentsDTO;
import com.easypatient.easypatient.dto.PatientMedicamentsGetDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface PatientMedicamentsDao {
    void insertPatientMedicaments(PatientMedicamentsDTO patientMedicaments);

    List<PatientMedicamentsGetDTO> selectAllPatientMedicaments();

    void deletePatientMedicamentsByIds(UUID patientId, UUID medicamentsId);

    List<PatientMedicamentsGetDTO> selectPatientMedicamentsByPatientId(UUID patientId) throws SQLException;

    List<PatientMedicamentsGetDTO> selectPatientMedicamentsByMedicamentsId(UUID medicamentsId) throws SQLException;
}
