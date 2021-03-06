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
    final String sqlSelectStaffByVariable = "SELECT id, name, email, phone, phone_area_code, password, role, created_at, updated_at FROM staff WHERE";
    final String sqlEmail = "(email = ?)";
    final String sqlName = "(name = ?)";
    final String sqlCreatedAfter = "(created_at >= ?)";
    final String sqlUpdatedAfter = "(updated_at >= ?)";
    final String sqlPhone = "(phone = ?)";
    final String sqlPhoneAreaCode = "(phone_area_code = ?)";
    final String sqlPassword = "(password = ?)";
    final String sqlRole = "(role = ?)";
    final String sqlAnd = "AND";
    final String sqlSemicolon = ";";
    final String sqlDeleteStaff = "DELETE FROM staff WHERE id = ?";
    final String sqlUpdateStaffById = "UPDATE staff SET name = ?, email = ?, phone = ?, phone_area_code = ?, password = ?, role = ?, updated_at = ? WHERE id = ?";


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
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sqlDeleteStaff, args);
    }

    @Override
    public void updateStaffById(UUID id,
                                Optional<String> name,
                                Optional<String> email,
                                Optional<String> phone,
                                Optional<String> phoneAreaCode,
                                Optional<String> password,
                                Optional<String> role) throws SQLException {
        Optional<StaffGetDTO> existingStaffOptional = selectStaffById(id);
        StaffGetDTO existingStaff;
        String nameToUpdate;
        String emailToUpdate;
        String phoneToUpdate;
        String phoneAreaCodeToUpdate;
        String passwordToUpdate;
        String roleToUpdate;
        LocalDateTime updatedAtToUpdate;

        if(existingStaffOptional.isPresent()) {
            existingStaff = existingStaffOptional.get();
        } else {
            throw new SQLException("Staff with ID: " + " doesn't exist.");
        }

        nameToUpdate = name.orElseGet(existingStaff::getName);
        emailToUpdate = email.orElseGet(existingStaff::getEmail);
        phoneToUpdate = phone.orElseGet(existingStaff::getPhone);
        phoneAreaCodeToUpdate = phoneAreaCode.orElseGet(existingStaff::getPhoneAreaCode);
        passwordToUpdate = password.orElseGet(existingStaff::getPassword);
        roleToUpdate = role.orElseGet(existingStaff::getRole);
        updatedAtToUpdate = LocalDateTime.now();

        jdbcTemplate.update(sqlUpdateStaffById,
                nameToUpdate,
                emailToUpdate,
                phoneToUpdate,
                phoneAreaCodeToUpdate,
                passwordToUpdate,
                roleToUpdate,
                updatedAtToUpdate,
                id);
    }

    @Override
    public Optional<StaffGetDTO> selectStaffById(UUID id) {
        StaffGetDTO staff = jdbcTemplate.queryForObject(
                sqlSelectStaffByID,
                new Object[]{id},
                StaffDataBaseAccessService::mapRow);
        return Optional.ofNullable(staff);
    }

    @Override
    public List<StaffGetDTO> selectStaffByVariables(Optional<String> name,
                                                    Optional<String> email,
                                                    Optional<String> phone,
                                                    Optional<String> phoneAreaCode,
                                                    Optional<String> password,
                                                    Optional<String> role,
                                                    Optional<LocalDateTime> createdAfter,
                                                    Optional<LocalDateTime> updatedAfter) throws SQLException {
        int i = 0;
        int k = 0;

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectStaffByVariable));

        if(name.isPresent()) {
            i++;
            expressions.add(sqlName);
        }
        if(email.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlEmail);
        }
        if(phone.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlPhone);
        }
        if(phoneAreaCode.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlPhoneAreaCode);
        }
        if(password.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlPassword);
        }
        if(role.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlRole);
        }
        if(createdAfter.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlCreatedAfter);
        }
        if(updatedAfter.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlUpdatedAfter);
        }

        String sqlExpression = String.join(" ", expressions);
        sqlExpression = sqlExpression.concat(sqlSemicolon);

        if(i != 0) {
            Object[] jdbcTable = new Object[i];
            if(name.isPresent()) {
                jdbcTable[k] = name.get();
                k++;
            }
            if(email.isPresent()) {
                jdbcTable[k] = email.get();
                k++;
            }
            if(phone.isPresent()) {
                jdbcTable[k] = phone.get();
                k++;
            }
            if(phoneAreaCode.isPresent()) {
                jdbcTable[k] = phoneAreaCode.get();
                k++;
            }
            if(password.isPresent()) {
                jdbcTable[k] = password.get();
                k++;
            }
            if(role.isPresent()) {
                jdbcTable[k] = role.get();
                k++;
            }
            if(createdAfter.isPresent()) {
                jdbcTable[k] = createdAfter.get();
                k++;
            }
            if(updatedAfter.isPresent()) {
                jdbcTable[k] = updatedAfter.get();
                k++;
            }
            return jdbcTemplate.query(
                    sqlExpression,
                    jdbcTable,
                    StaffDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }
    }
}
