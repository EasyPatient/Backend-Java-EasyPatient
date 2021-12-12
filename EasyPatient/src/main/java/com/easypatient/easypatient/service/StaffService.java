package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.StaffDao;
import com.easypatient.easypatient.dto.StaffDTO;
import com.easypatient.easypatient.dto.StaffGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffService {
    private final StaffDao staffDao;

    @Autowired
    public StaffService(@Qualifier("StaffPostgres") StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public void addStaff(StaffDTO staff) {
        staffDao.insertStaff(staff);
    }

    public List<StaffGetDTO> getAllStaff() {
        return staffDao.selectAllStaff();
    }

    public Optional<StaffGetDTO> getStaffById(UUID id) {
        return staffDao.selectStaffById(id);
    }

    public List<StaffGetDTO> getStaffByVariables(Optional<String> name,
                                                 Optional<String> email,
                                                 Optional<String> phone,
                                                 Optional<String> phoneAreaCode,
                                                 Optional<String> password,
                                                 Optional<String> role,
                                                 Optional<LocalDateTime> createdAfter,
                                                 Optional<LocalDateTime> updatedAfter) throws SQLException {
        return staffDao.selectStaffByVariables(name, email, phone, phoneAreaCode, password, role, createdAfter, updatedAfter);
    }

    public void deleteStaff(UUID id) {
        staffDao.deleteStaffById(id);
    }

    public void updateStaff(UUID id,
                            Optional<String> name,
                            Optional<String> email,
                            Optional<String> phone,
                            Optional<String> phoneAreaCode,
                            Optional<String> password,
                            Optional<String> role) throws SQLException {
        staffDao.updateStaffById(id, name, email, phone, phoneAreaCode, password, role);
    }

}
