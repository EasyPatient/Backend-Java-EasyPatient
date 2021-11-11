package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Staff;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StaffDao {
    void insertStaff(UUID id, Staff staff);

    List<Staff> selectAllStaffs();

    void deleteStaffById(UUID id);

    void updateStaffById(UUID id, Staff staff);

    Optional<Staff> selectStaffById(UUID id);
}
