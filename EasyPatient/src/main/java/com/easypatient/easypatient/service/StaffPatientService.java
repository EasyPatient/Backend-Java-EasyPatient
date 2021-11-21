package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.StaffPatientDao;
import com.easypatient.easypatient.dto.StaffPatientDTO;
import com.easypatient.easypatient.dto.StaffPatientGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffPatientService {
    private final StaffPatientDao staffPatientDao;

    @Autowired
    StaffPatientService(@Qualifier("StaffPatientPostgres") StaffPatientDao staffPatientDao) {
        this.staffPatientDao = staffPatientDao;
    }

    public void addStaffPatient(StaffPatientDTO staffPatient) {
        staffPatientDao.insertStaffPatient(staffPatient);
    }

    public List<StaffPatientGetDTO> getAllStaffPatient() {
        return staffPatientDao.selectAllStaffPatient();
    }

    public List<StaffPatientGetDTO> getStaffPatientByPatientId(UUID patientId) throws SQLException {
        return staffPatientDao.selectStaffPatientByPatientId(patientId);
    }

    public List<StaffPatientGetDTO> getStaffPatientByStaffId(UUID staffId) throws SQLException {
        return staffPatientDao.selectStaffPatientByStaffId(staffId);
    }

    public void deleteStaffPatient(UUID patientId, UUID staffId) {
        staffPatientDao.deleteStaffPatientByIds(patientId, staffId);
    }



}
