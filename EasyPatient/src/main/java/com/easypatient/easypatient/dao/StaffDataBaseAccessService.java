package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("StaffPostgres")
public class StaffDataBaseAccessService implements StaffDao {
    @Override
    public void insertStaff(UUID id, Staff staff) {
    }

    @Override
    public List<Staff> selectAllStaffs() {
        return List.of();
    }

    @Override
    public void deleteStaffById(UUID id) {
    }

    @Override
    public void updateStaffById(UUID id, Staff staff) {
    }

    @Override
    public Optional<Staff> selectStaffById(UUID id) {
        return Optional.ofNullable(Staff.builder().build());
    }
}
