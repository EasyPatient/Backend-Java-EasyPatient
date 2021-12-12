package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.PatientDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public void addPatient(@Valid @NonNull @RequestBody PatientDTO patient) throws SQLException {
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
    public List<PatientGetDTO> getPatientsByVariables(@RequestParam(required = false) Optional<String> name,
                                                      @RequestParam(required = false) Optional<Integer> age,
                                                      @RequestParam(required = false) Optional<UUID> bedId,
                                                      @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                  Optional<LocalDateTime> arrivedAfter,
                                                      @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                  Optional<LocalDateTime> createdAfter,
                                                      @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                  Optional<LocalDateTime> updatedAfter) throws SQLException {
        return patientService.getPatientByVariables(name,
                age,
                bedId,
                arrivedAfter,
                createdAfter,
                updatedAfter);
    }

    @DeleteMapping(path = "{id}")
    public void deletePatientById(@PathVariable("id") UUID id) {
        patientService.deletePatient(id);
    }

    @PutMapping(path = "{id}")
    public void updatePatientById(@PathVariable("id") UUID id,
                                  @RequestParam(required = false) Optional<String> name,
                                  @RequestParam(required = false) Optional<Integer> age,
                                  @RequestParam(required = false) Optional<UUID> bedId,
                                  @RequestParam(required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                              Optional<LocalDateTime> arrivedAt) throws SQLException {
        patientService.updatePatient(id, name, age, bedId, arrivedAt);
    }
}
