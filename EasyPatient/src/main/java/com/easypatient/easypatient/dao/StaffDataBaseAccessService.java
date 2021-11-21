package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.dto.StaffDTO;
import com.easypatient.easypatient.dto.StaffGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("StaffPostgres")
public class StaffDataBaseAccessService implements StaffDao {

    final String sqlSelectAllStaff = "SELECT id, name, email, phone, phone_area_code, password, role, created_at, updated_at FROM staff";
    final String sqlSelectStaffByID = "SELECT id, name, email, phone, phone_area_code, password, role, created_at, updated_at FROM staff WHERE id = ?";
    final String sqlInsertStaff = "INSERT INTO staff VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StaffDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static StaffGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String phone_area_code = resultSet.getString("phone_area_code");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return StaffGetDTO.builder()
                .id(id)
                .name(name)
                .email(email)
                .phone(phone)
                .phoneAreaCode(phone_area_code)
                .password(password)
                .role(role)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertStaff(StaffDTO staff) {
        LocalDateTime date = LocalDateTime.now();

        jdbcTemplate.update(sqlInsertStaff,
                staff.getName(),
                staff.getEmail(),
                staff.getPhone(),
                staff.getPhoneAreaCode(),
                staff.getPassword(),
                staff.getRole(),
                date,
                date);
    }

    @Override
    public List<StaffGetDTO> selectAllStaff() {
        return jdbcTemplate.query(sqlSelectAllStaff,
                StaffDataBaseAccessService::mapRow);

    }

    @Override
    public void deleteStaffById(UUID id) {
    }

    @Override
    public void updateStaffById(UUID id,
                                Optional<String> name,
                                Optional<String> email,
                                Optional<String> phone,
                                Optional<String> phoneAreaCode,
                                Optional<String> password,
                                Optional<String> role) throws SQLException {
    }

    @Override
    public Optional<StaffGetDTO> selectStaffById(UUID id) {
        StaffGetDTO staff = jdbcTemplate.queryForObject(
                sqlSelectStaffByID,
                new Object[]{id},
                StaffDataBaseAccessService::mapRow);
        return Optional.ofNullable(StaffGetDTO.builder().build());
    }

    @Override
    public List<StaffGetDTO> selectStaffByVariables(Optional<String> name,
                                                    Optional<String> email,
                                                    Optional<String> phone,
                                                    Optional<String> phoneAreaCode,
                                                    Optional<String> password,
                                                    Optional<String> role,
                                                    Optional<LocalDateTime> createdAt,
                                                    Optional<LocalDateTime> updatedAt) throws SQLException {
        return List.of();
    }
}
