package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.StaffPatient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("StaffPatientPostgres")
public class StaffPatientDataBaseAccessService implements StaffPatientDao{
    @Override
    public void insertStaffPatient(UUID id, StaffPatient staffPatient) {
    }

    @Override
    public List<StaffPatient> selectAllStaffPatients() {
        return List.of();
    }

    @Override
    public void deleteStaffPatientById(UUID id) {
    }

    @Override
    public void updateStaffPatientById(UUID id, StaffPatient staffPatient) {
    }

    @Override
    public Optional<StaffPatient> selectStaffPatientById(UUID id) {
        return Optional.ofNullable(StaffPatient.builder().build());
    }
}
