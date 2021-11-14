package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.PatientDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public void addPatient(@Valid @NonNull @RequestBody PatientDTO patient) {
        patientService.addPatient(patient);
    }

    @GetMapping
    public List<PatientGetDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(path = "{id}")
    public PatientGetDTO getPatientById(@PathVariable("id") UUID id) {
        return patientService.getPatientById(id)
                .orElse(null);
    }

    @GetMapping(path = "/getByVariables")
    public PatientGetDTO getPatientsByVariables(@RequestParam(required = false) Optional<String> name,
                                                @RequestParam(required = false) Optional<Integer> age,
                                                @RequestParam(required = false) Optional<UUID> bedId,
                                                @RequestParam(required = false) Optional<LocalDateTime> arrivedAt,
                                                @RequestParam(required = false) Optional<LocalDateTime> createdAt,
                                                @RequestParam(required = false) Optional<LocalDateTime> updatedAt) throws SQLException {
        return patientService.getPatientByVariables(name,
                                                    age,
                                                    bedId,
                                                    arrivedAt,
                                                    createdAt,
                                                    updatedAt).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePatientById(@PathVariable("id") UUID id) {
        patientService.deletePatient(id);
    }

    @PutMapping(path = "{id}")
    public void updatePatient(@PathVariable("id") UUID id,
                              @RequestParam(required = false) Optional<String> name,
                              @RequestParam(required = false) Optional<Integer> age,
                              @RequestParam(required = false) Optional<UUID> bedId,
                              @RequestParam(required = false) Optional<LocalDateTime> arrivedAt) throws SQLException {
        patientService.updatePatient(id, name, age, bedId, arrivedAt);
    }
}
