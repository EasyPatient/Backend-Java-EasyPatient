package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.StaffDTO;
import com.easypatient.easypatient.dto.StaffGetDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StaffDao {
    void insertStaff(StaffDTO staff);

    List<StaffGetDTO> selectAllStaff();

    void deleteStaffById(UUID id);

    void updateStaffById(UUID id,
                         Optional<String> name,
                         Optional<String> email,
                         Optional<String> phone,
                         Optional<String> phoneAreaCode,
                         Optional<String> password,
                         Optional<String> role) throws SQLException;

    Optional<StaffGetDTO> selectStaffById(UUID id);

    List<StaffGetDTO> selectStaffByVariables(Optional<String> name,
                                             Optional<String> email,
                                             Optional<String> phone,
                                             Optional<String> phoneAreaCode,
                                             Optional<String> password,
                                             Optional<String> role,
                                             Optional<LocalDateTime> createdAfter,
                                             Optional<LocalDateTime> updatedAfter) throws SQLException;
}
