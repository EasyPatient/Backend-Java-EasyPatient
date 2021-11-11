package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PatientPostgres")
public class PatientDataBaseAccessService implements PatientDao {

    final String sqlSelectAllPeople = "SELECT id, name, age, bed_id, arrived_at, created_at, updated_at FROM patient1";
    final String sqlSelectPatientByID = "SELECT id, name, age, bed_id, arrived_at, created_at, updated_at FROM patient1 WHERE id = ?";
    final String sqlInsertPatient = "INSERT INTO patient1 VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String sqlDeletePatient = "DELETE FROM patient1 WHERE id = ?";
    final String sqlUpdatePatientById = "UPDATE patient1 SET name = ?, age = ?, bed_id = ?, arrived_at = ?, created_at = ?, updated_at = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Patient mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        UUID bedId = UUID.fromString((resultSet.getString("bed_id")));
        LocalDateTime arrivedAt = resultSet.getTimestamp("arrived_at").toLocalDateTime();
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return Patient.builder()
                .id(id)
                .name(name)
                .age(age)
                .bedId(bedId)
                .arrivedAt(arrivedAt)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertPatient(UUID id, Patient patient) {
        jdbcTemplate.update(sqlInsertPatient,
                id,
                patient.getName(),
                patient.getAge(),
                patient.getBedId(),
                patient.getArrivedAt(),
                patient.getCreatedAt(),
                patient.getUpdatedAt());
    }

    @Override
    public List<Patient> selectAllPatients() {
        return jdbcTemplate.query(sqlSelectAllPeople,
                PatientDataBaseAccessService::mapRow);
    }

    @Override
    public void deletePatientById(UUID id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sqlDeletePatient, args);
    }

    @Override
    public void updatePatientById(UUID id, Patient patient) {
        jdbcTemplate.update(sqlUpdatePatientById,
                patient.getName(),
                patient.getAge(),
                patient.getBedId(),
                patient.getArrivedAt(),
                patient.getCreatedAt(),
                patient.getUpdatedAt(),
                id);
    }

    @Override
    public Optional<Patient> selectPatientById(UUID id) {
        Patient patient = jdbcTemplate.queryForObject(
                sqlSelectPatientByID,
                new Object[]{id},
                PatientDataBaseAccessService::mapRow);
        return Optional.ofNullable(patient);
    }

}
