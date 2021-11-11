package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.StaffPatient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StaffPatientDao {
    void insertStaffPatient(UUID id, StaffPatient staffPatient);

    List<StaffPatient> selectAllStaffPatients();

    void deleteStaffPatientById(UUID id);

    void updateStaffPatientById(UUID id, StaffPatient staffPatient);

    Optional<StaffPatient> selectStaffPatientById(UUID id);
}
