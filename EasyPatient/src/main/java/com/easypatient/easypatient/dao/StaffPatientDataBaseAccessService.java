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

@Repository("StaffPatientPostgres")
public class StaffPatientDataBaseAccessService implements StaffPatientDao{

    final String sqlSelectAllStaffPatient = "SELECT patient_id, staff_id, created_at, updated_at FROM staff_patient";
    final String sqlSelectStaffPatientByPatientId = "SELECT patient_id, staff_id, created_at, updated_at FROM staff_patient WHERE patient_id = ?";
    final String sqlSelectStaffPatientByStaffId = "SELECT patient_id, staff_id, created_at, updated_at FROM staff_patient WHERE staff_id = ?";
    final String sqlInsertStaffPatient = "INSERT INTO staff_patient  VALUES(?, ?, ?, ?)";
    final String sqlDeleteStaffPatient = "DELETE FROM staff_patient WHERE patient_id = ? AND staff_id = ?";


    private final JdbcTemplate jdbcTemplate;
    private final PatientDataBaseAccessService patientDataBaseAccessService;
    private final StaffDataBaseAccessService staffDataBaseAccessService;

    @Autowired
    public StaffPatientDataBaseAccessService(JdbcTemplate jdbcTemplate,
                                             PatientDataBaseAccessService patientDataBaseAccessService,
                                             StaffDataBaseAccessService staffDataBaseAccessService) {
        this.jdbcTemplate = jdbcTemplate;
        this.patientDataBaseAccessService = patientDataBaseAccessService;
        this.staffDataBaseAccessService = staffDataBaseAccessService;
    }

    private static StaffPatientGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID patient_id = UUID.fromString(resultSet.getString("patient_id"));
        UUID staff_id = UUID.fromString(resultSet.getString("staff_id"));
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return StaffPatientGetDTO.builder()
                .patientId(patient_id)
                .staffId(staff_id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertStaffPatient(StaffPatientDTO staffPatient) throws SQLException {
        LocalDateTime date = LocalDateTime.now();

        UUID patientId = staffPatient.getPatientId();
        UUID staffId = staffPatient.getStaffId();

        Optional<PatientGetDTO> patientFromDB = patientDataBaseAccessService.selectPatientById(patientId);
        Optional<StaffGetDTO> staffFromDB = staffDataBaseAccessService.selectStaffById(staffId);

        if(patientFromDB.isPresent() && staffFromDB.isPresent()) {
            jdbcTemplate.update(sqlInsertStaffPatient,
                    staffPatient.getPatientId(),
                    staffPatient.getStaffId(),
                    date,
                    date);
        } else {
            throw new SQLException("can not insert staff patient with patient ID: " + patientId + ", and staff ID: " + staffId);
        }

    }

    @Override
    public List<StaffPatientGetDTO> selectAllStaffPatient() {

        return jdbcTemplate.query(sqlSelectAllStaffPatient,
                StaffPatientDataBaseAccessService::mapRow);

    }

    @Override
    public void deleteStaffPatientByIds(UUID patientId, UUID staffId) {
        Object[] args = new Object[]{patientId, staffId};
        jdbcTemplate.update(sqlDeleteStaffPatient, args);
    }

    @Override
    public List<StaffPatientGetDTO> selectStaffPatientByPatientId(UUID patientId) throws SQLException {
        StaffPatientGetDTO staffPatient = jdbcTemplate.queryForObject(
                sqlSelectStaffPatientByPatientId,
                new Object[]{patientId},
                StaffPatientDataBaseAccessService::mapRow);
        return List.of();
    }

    @Override
    public List<StaffPatientGetDTO> selectStaffPatientByStaffId(UUID stafftId) throws SQLException {
        StaffPatientGetDTO staffPatient = jdbcTemplate.queryForObject(
                sqlSelectStaffPatientByStaffId,
                new Object[]{stafftId},
                StaffPatientDataBaseAccessService::mapRow);
        return List.of();
    }

}
