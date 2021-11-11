package com.easypatient.easypatient.api;

import com.easypatient.easypatient.model.Patient;
import com.easypatient.easypatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/patient")
@RestController
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public void addPatient(@Valid @NonNull @RequestBody Patient patient) {
        patientService.addPatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPeople() {
        return patientService.getAllPeople();
    }

    @GetMapping("{id}")
    public Patient getPatientById(@PathVariable("id") UUID id) {
        return patientService.getPatientById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePatientById(@PathVariable("id") UUID id) {
        patientService.deletePatient(id);
    }

    @PutMapping(path = "{id}")
    public void updatePatient(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Patient PatientToUpdate) {
        patientService.updatePatient(id, PatientToUpdate);
    }
}
