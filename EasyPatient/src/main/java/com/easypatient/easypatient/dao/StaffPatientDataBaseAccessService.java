package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.StaffPatientDTO;
import com.easypatient.easypatient.dto.StaffPatientGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository("StaffPatientPostgres")
public class StaffPatientDataBaseAccessService implements StaffPatientDao{
    @Override
    public void insertStaffPatient(StaffPatientDTO staffPatient) {
    }

    @Override
    public List<StaffPatientGetDTO> selectAllStaffPatient() {
        return List.of();
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
