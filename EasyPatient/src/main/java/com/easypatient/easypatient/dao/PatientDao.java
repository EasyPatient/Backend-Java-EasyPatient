package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientDao {
    void insertPatient(PatientDTO patient) throws SQLException;

    List<PatientGetDTO> selectAllPatients();

    void deletePatientById(UUID id);

    void updatePatientById(UUID id,
                           Optional<String> name,
                           Optional<Integer> age,
                           Optional<UUID> bedId,
                           Optional<LocalDateTime> arrivedAt) throws SQLException;

    Optional<PatientGetDTO> selectPatientById(UUID id);

    List<PatientGetDTO> selectPatientByVariables(Optional<String> name,
                                                     Optional<Integer> age,
                                                     Optional<UUID> bedId,
                                                     Optional<LocalDateTime> arrivedAt,
                                                     Optional<LocalDateTime> createdAt,
                                                     Optional<LocalDateTime> updatedAt) throws SQLException;
}
