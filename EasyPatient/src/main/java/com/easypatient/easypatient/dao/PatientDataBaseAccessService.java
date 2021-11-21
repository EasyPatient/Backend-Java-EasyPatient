package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("PatientPostgres")
public class PatientDataBaseAccessService implements PatientDao {

    final String sqlSelectAllPeople = "SELECT id, name, age, bed_id, arrived_at, created_at, updated_at FROM patient";
    final String sqlSelectPatientByID = "SELECT id, name, age, bed_id, arrived_at, created_at, updated_at FROM patient WHERE id = ?";
    final String sqlSelectPatientByVariable = "SELECT id, name, age, bed_id, arrived_at, created_at, updated_at FROM patient WHERE ";
    final String sqlName = " name = ?";
    final String sqlAge = " AND age = ?";
    final String sqlBed = " AND bed_id = ?";
    final String sqlArrivedAt = " AND arrived_at = ?";
    final String sqlCreatedAt = " AND created_at = ?";
    final String sqlUpdatedAt = " AND updated_at = ?";
    final String sqlSemicolon = ";";
    final String sqlInsertPatient = "INSERT INTO patient VALUES(?, ?, ?, ?, ?, ?)";
    final String sqlDeletePatient = "DELETE FROM patient WHERE id = ?";
    final String sqlUpdatePatientById = "UPDATE patient SET name = ?, age = ?, bed_id = ?, arrived_at = ?, updated_at = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static PatientGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        UUID bedId = UUID.fromString((resultSet.getString("bed_id")));
        LocalDateTime arrivedAt = resultSet.getTimestamp("arrived_at").toLocalDateTime();
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return PatientGetDTO.builder()
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
    public void insertPatient(PatientDTO patient) {
        LocalDateTime date = LocalDateTime.now();

        jdbcTemplate.update(sqlInsertPatient,
                patient.getName(),
                patient.getAge(),
                patient.getBedId(),
                patient.getArrivedAt(),
                date,
                date);
    }

    @Override
    public List<PatientGetDTO> selectAllPatients() {
        return jdbcTemplate.query(sqlSelectAllPeople,
                PatientDataBaseAccessService::mapRow);
    }

    @Override
    public void deletePatientById(UUID id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sqlDeletePatient, args);
    }

    @Override
    public void updatePatientById(UUID id,
                                  Optional<String> name,
                                  Optional<Integer> age,
                                  Optional<UUID> bedId,
                                  Optional<LocalDateTime> arrivedAt) throws SQLException {
        Optional<PatientGetDTO> existingPatientOptional = selectPatientById(id);
        PatientGetDTO existingPatient;
        String nameToUpdate;
        int ageToUpdate;
        UUID bedIdToUpdate;
        LocalDateTime arrivedAtToUpdate;
        LocalDateTime updatedAtToUpdate;

        if (existingPatientOptional.isPresent()) {
            existingPatient = existingPatientOptional.get();
        } else {
            throw new SQLException("Patient with ID:" + id + " does not exist.");
        }

        nameToUpdate = ((name.orElseGet(existingPatient::getName)));
        ageToUpdate = (age.orElseGet(existingPatient::getAge));
        bedIdToUpdate = (bedId.orElseGet(existingPatient::getBedId));
        arrivedAtToUpdate = (arrivedAt.orElseGet(existingPatient::getArrivedAt));
        updatedAtToUpdate = LocalDateTime.now();

        jdbcTemplate.update(sqlUpdatePatientById,
                nameToUpdate,
                ageToUpdate,
                bedIdToUpdate,
                arrivedAtToUpdate,
                updatedAtToUpdate,
                id);
    }

    @Override
    public Optional<PatientGetDTO> selectPatientById(UUID id) {
        PatientGetDTO patient = jdbcTemplate.queryForObject(
                sqlSelectPatientByID,
                new Object[]{id},
                PatientDataBaseAccessService::mapRow);
        return Optional.ofNullable(patient);
    }

    @Override
    public List<PatientGetDTO> selectPatientByVariables(Optional<String> name,
                                                        Optional<Integer> age,
                                                        Optional<UUID> bedId,
                                                        Optional<LocalDateTime> arrivedAt,
                                                        Optional<LocalDateTime> createdAt,
                                                        Optional<LocalDateTime> updatedAt) throws SQLException {
        int i = 0;
        int k = 0;

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectPatientByVariable));

        String sql = sqlSelectPatientByVariable;
        if(name.isPresent()) {
            i++;
            sql.concat(sqlName);
            expressions.add(sqlName);
        }
        if(age.isPresent()) {
            i++;
            sql.concat(sqlAge);
            expressions.add(sqlAge);
        }
        if(bedId.isPresent()) {
            i++;
            sql.concat(sqlBed);
            expressions.add(sqlBed);
        }
        if(arrivedAt.isPresent()) {
            i++;
            sql.concat(sqlArrivedAt);
            expressions.add(sqlArrivedAt);
        }
        if(createdAt.isPresent()) {
            i++;
            sql.concat(sqlCreatedAt);
            expressions.add(sqlCreatedAt);
        }
        if(updatedAt.isPresent()) {
            i++;
            sql.concat(sqlUpdatedAt);
            expressions.add(sqlUpdatedAt);
        }

        expressions.add(sqlSemicolon);
        String sqlExpression = String.join(" ", expressions);

        if(i != 0) {
            Object[] jdbcTable = new Object[i];
            if(name.isPresent()) {
                jdbcTable[k] = name.get();
                k++;
            }
            if(age.isPresent()) {
                jdbcTable[k] = age.get();
                k++;
            }
            if(bedId.isPresent()) {
                jdbcTable[k] = bedId.get();
                k++;
            }
            if(arrivedAt.isPresent()) {
                jdbcTable[k] = arrivedAt.get();
                k++;
            }
            if(createdAt.isPresent()) {
                jdbcTable[k] = createdAt.get();
                k++;
            }
            if(updatedAt.isPresent()) {
                jdbcTable[k] = updatedAt.get();
                k++;
            }
            return jdbcTemplate.query(
                    sqlExpression,
                    jdbcTable,
                    PatientDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }




    }



}
