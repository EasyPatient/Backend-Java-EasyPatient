package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.dto.StaffPatientDTO;
import com.easypatient.easypatient.dto.StaffPatientGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository("StaffPatientPostgres")
public class StaffPatientDataBaseAccessService implements StaffPatientDao{

    final String sqlSelectAllStaffPatient = "SELECT patient_id, staff_id, created_at, updated_at FROM patient";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StaffPatientDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public void insertStaffPatient(StaffPatientDTO staffPatient) {
    }

    @Override
    public List<StaffPatientGetDTO> selectAllStaffPatient() {

        return jdbcTemplate.query(sqlSelectAllStaffPatient,
                StaffPatientDataBaseAccessService::mapRow);

    }

    @Override
    public void deleteStaffPatientByIds(UUID patientId, UUID staffId) {
    }

    @Override
    public List<StaffPatientGetDTO> selectStaffPatientByPatientId(UUID patientId) throws SQLException {
        return List.of();
    }

    @Override
    public List<StaffPatientGetDTO> selectStaffPatientByStaffId(UUID stafftId) throws SQLException {
        return List.of();
    }

}
