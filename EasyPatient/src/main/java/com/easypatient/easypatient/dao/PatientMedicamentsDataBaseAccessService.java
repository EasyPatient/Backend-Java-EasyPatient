package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PatientMedicamentsPostgres")
public class PatientMedicamentsDataBaseAccessService implements PatientMedicamentsDao {

    final String sqlSelectAllPatientMedicaments = "SELECT patient_id, medicaments_id, created_at, updated_at FROM patient_medicaments";
    final String sqlSelectPatientMedicamentsByPatientId = "SELECT patient_id, medicaments_id, created_at, updated_at FROM patient_medicaments WHERE patient_id = ?";
    final String sqlSelectPatientMedicamentsByMedicamentsId = "SELECT patient_id, medicaments_id, created_at, updated_at FROM patient_medicaments WHERE medicaments_id = ?";
    final String sqlInsertPatientMedicaments = "INSERT INTO patient_medicaments VALUES(?, ?, ?, ?)";
    final String sqlDeletePatientMedicaments = "DELETE FROM patient_medicaments WHERE patient_id = ? AND (medicaments_id = ?)";


    private final JdbcTemplate jdbcTemplate;
    private final PatientDataBaseAccessService patientDataBaseAccessService;
    private final MedicamentsDataBaseAccessService medicamentsDataBaseAccessService;

    @Autowired
    public PatientMedicamentsDataBaseAccessService(JdbcTemplate jdbcTemplate,
                                                   PatientDataBaseAccessService patientDataBaseAccessService,
                                                   MedicamentsDataBaseAccessService medicamentsDataBaseAccessService) {
        this.jdbcTemplate = jdbcTemplate;
        this.patientDataBaseAccessService = patientDataBaseAccessService;
        this.medicamentsDataBaseAccessService = medicamentsDataBaseAccessService;
    }

    private static PatientMedicamentsGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID patient_id = UUID.fromString(resultSet.getString("patient_id"));
        UUID medicaments_id = UUID.fromString(resultSet.getString("medicaments_id"));
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return PatientMedicamentsGetDTO.builder()
                .patientId(patient_id)
                .medicamentsId(medicaments_id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertPatientMedicaments(PatientMedicamentsDTO patientMedicaments) throws SQLException {

        LocalDateTime date = LocalDateTime.now();

        UUID patientId = patientMedicaments.getPatientId();
        UUID medicamentsId = patientMedicaments.getMedicamentsId();

        Optional<PatientGetDTO> patientFromDB = patientDataBaseAccessService.selectPatientById(patientId);
        Optional<MedicamentsGetDTO> medicamentsFromDB = medicamentsDataBaseAccessService.selectMedicamentsById(medicamentsId);

        if(patientFromDB.isPresent() && medicamentsFromDB.isPresent()) {
            jdbcTemplate.update(sqlInsertPatientMedicaments,
                    patientMedicaments.getPatientId(),
                    patientMedicaments.getMedicamentsId(),
                    date,
                    date);
        } else {
            throw new SQLException("can not insert patient Medicaments with patient ID: " + patientId + ", and medicament ID: " + medicamentsId);
        }
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectAllPatientMedicaments() {
        return jdbcTemplate.query(sqlSelectAllPatientMedicaments,
                PatientMedicamentsDataBaseAccessService::mapRow);
    }

    @Override
    public void deletePatientMedicamentsByIds(UUID patientId, UUID medicamentsId) {
        Object[] args = new Object[]{patientId, medicamentsId};
        jdbcTemplate.update(sqlDeletePatientMedicaments, args);
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectPatientMedicamentsByPatientId(UUID patientId) {
        return jdbcTemplate.query(
                sqlSelectPatientMedicamentsByPatientId,
                new Object[]{patientId},
                PatientMedicamentsDataBaseAccessService::mapRow);
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectPatientMedicamentsByMedicamentsId(UUID medicamentsId) {
        return jdbcTemplate.query(
                sqlSelectPatientMedicamentsByMedicamentsId,
                new Object[]{medicamentsId},
                PatientMedicamentsDataBaseAccessService::mapRow);
    }
}
