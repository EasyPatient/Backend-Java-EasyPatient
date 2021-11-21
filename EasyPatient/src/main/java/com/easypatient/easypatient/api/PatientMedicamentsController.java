package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.PatientMedicamentsDTO;
import com.easypatient.easypatient.dto.PatientMedicamentsGetDTO;
import com.easypatient.easypatient.service.PatientMedicamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/patientMedicaments")
@RestController
public class PatientMedicamentsController {
    private final PatientMedicamentsService patientMedicamentsService;

    @Autowired
    public PatientMedicamentsController(PatientMedicamentsService patientMedicamentsService) {
        this.patientMedicamentsService = patientMedicamentsService;
    }

    @PostMapping
    public void addPatientMedicaments(@Valid @NonNull @RequestBody PatientMedicamentsDTO patientMedicaments) {
        patientMedicamentsService.addPatientMedicaments(patientMedicaments);
    }

    @GetMapping
    public List<PatientMedicamentsGetDTO> getAllPatientMedicaments() {
        return patientMedicamentsService.getAllPatientMedicaments();
    }

    @GetMapping(path = "{patientId}")
    public List<PatientMedicamentsGetDTO> getPatientMedicamentsByPatientId(@PathVariable("patientId") UUID patientId) throws SQLException {
        return patientMedicamentsService.getPatientMedicamentsByPatientId(patientId);
    }

    @GetMapping(path = "{medicamentsId}")
    public List<PatientMedicamentsGetDTO> getPatientMedicamentsByMedicamentsId(@PathVariable("medicamentsId") UUID medicamentsId) throws SQLException {
        return patientMedicamentsService.getPatientMedicamentsByMedicamentsId(medicamentsId);
    }

    @DeleteMapping(path = "{patientId}/{medicamentsId}")
    public void deletePatientMedicamentsByIds(@PathVariable("patientId") UUID patientId, @PathVariable("medicamentsId") UUID medicamentsId) {
        patientMedicamentsService.deletePatientMedicaments(patientId, medicamentsId);
    }
}
