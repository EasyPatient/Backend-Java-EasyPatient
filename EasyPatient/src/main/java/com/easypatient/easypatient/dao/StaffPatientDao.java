package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.StaffPatientDTO;
import com.easypatient.easypatient.dto.StaffPatientGetDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface StaffPatientDao {
    void insertStaffPatient(StaffPatientDTO staffPatient);

    List<StaffPatientGetDTO> selectAllStaffPatient();

    void deleteStaffPatientByIds(UUID patientId, UUID staffId);

    List<StaffPatientGetDTO> selectStaffPatientByPatientId(UUID patientId) throws SQLException;

    List<StaffPatientGetDTO> selectStaffPatientByStaffId(UUID staffId) throws SQLException;
}
