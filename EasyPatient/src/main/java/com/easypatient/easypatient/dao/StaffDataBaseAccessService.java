package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.StaffDTO;
import com.easypatient.easypatient.dto.StaffGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("StaffPostgres")
public class StaffDataBaseAccessService implements StaffDao {
    @Override
    public void insertStaff(StaffDTO staff) {
    }

    @Override
    public List<StaffGetDTO> selectAllStaff() {
        return List.of();
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
