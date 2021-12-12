package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.PatientDao;
import com.easypatient.easypatient.dto.PatientDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientDao patientDao;

    @Autowired
    public PatientService(@Qualifier("PatientPostgres") PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public void addPatient(PatientDTO patient) throws SQLException {
        int age = patient.getAge();
        if (age < 0) {
            throw new SQLException("Can not add patient with age less than 0.");
        }
        patientDao.insertPatient(patient);
    }

    public List<PatientGetDTO> getAllPatients() {
        return patientDao.selectAllPatients();
    }

    public Optional<PatientGetDTO> getPatientById(UUID id) {
        return patientDao.selectPatientById(id);
    }

    public List<PatientGetDTO> getPatientByVariables(Optional<String> name,
                                                         Optional<Integer> age,
                                                         Optional<UUID> bedId,
                                                         Optional<LocalDateTime> arrivedAt,
                                                         Optional<LocalDateTime> createdAt,
                                                         Optional<LocalDateTime> updatedAt) throws SQLException {
        return patientDao.selectPatientByVariables(name, age, bedId, arrivedAt, createdAt, updatedAt);
    }

    public void deletePatient(UUID id) {
        patientDao.deletePatientById(id);
    }

    public void updatePatient(UUID id,
                              Optional<String> name,
                              Optional<Integer> age,
                              Optional<UUID> bedId,
                              Optional<LocalDateTime> arrivedAt) throws SQLException {
        if (age.isPresent() && age.get() < 0) {
            throw new SQLException("Can not add patient with age less than 0.");
        }
        patientDao.updatePatientById(id, name, age, bedId, arrivedAt);
    }
}
