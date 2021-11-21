package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.dto.PatientMedicamentsDTO;
import com.easypatient.easypatient.dto.PatientMedicamentsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository("PatientMedicamentsPostgres")
public class PatientMedicamentsDataBaseAccessService implements PatientMedicamentsDao {

    final String sqlSelectAllPatientMedicaments = "SELECT patient_id, medicaments_id, created_at, updated_at FROM patient_medicaments";
    final String sqlSelectPatientMedicamentsByPatientId = "SELECT patient_id, medicaments_id, created_at, updated_at FROM patient_medicaments WHERE patient_id = ?";
    final String sqlSelectPatientMedicamentsByMedicamentsId = "SELECT patient_id, medicaments_id, created_at, updated_at FROM patient_medicaments WHERE medicaments_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientMedicamentsDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public void insertPatientMedicaments(PatientMedicamentsDTO patientMedicaments) {
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectAllPatientMedicaments() {
        return jdbcTemplate.query(sqlSelectAllPatientMedicaments,
                PatientMedicamentsDataBaseAccessService::mapRow);
    }

    @Override
    public void deletePatientMedicamentsByIds(UUID patientId, UUID medicamentsId) {
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectPatientMedicamentsByPatientId(UUID patientId) throws SQLException {
        PatientMedicamentsGetDTO patientMedicament = jdbcTemplate.queryForObject(
                sqlSelectPatientMedicamentsByPatientId,
                new Object[]{patientId},
                PatientMedicamentsDataBaseAccessService::mapRow);
        return List.of();
    }

    @Override
    public List<PatientMedicamentsGetDTO> selectPatientMedicamentsByMedicamentsId(UUID medicamentsId) throws SQLException {
        PatientMedicamentsGetDTO patientMedicament = jdbcTemplate.queryForObject(
                sqlSelectPatientMedicamentsByMedicamentsId,
                new Object[]{medicamentsId},
                PatientMedicamentsDataBaseAccessService::mapRow);
        return List.of();
    }
}
