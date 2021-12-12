package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.StaffPatientDTO;
import com.easypatient.easypatient.dto.StaffPatientGetDTO;
import com.easypatient.easypatient.service.StaffPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/staffPatient")
@RestController
public class StaffPatientController {
    private final StaffPatientService staffPatientService;

    @Autowired
    public StaffPatientController(StaffPatientService staffPatientService) {
        this.staffPatientService = staffPatientService;
    }

    @PostMapping
    public void addStaffPatient(@Valid @NonNull @RequestBody StaffPatientDTO staffPatient) throws SQLException {
        staffPatientService.addStaffPatient(staffPatient);
    }

    @GetMapping
    public List<StaffPatientGetDTO> getAllStaffPatient() {
        return staffPatientService.getAllStaffPatient();
    }

    @GetMapping(path = "/patientId/{patientId}")
    public List<StaffPatientGetDTO> getStaffPatientByPatientId(@PathVariable UUID patientId) throws SQLException {
        return staffPatientService.getStaffPatientByPatientId(patientId);
    }

    @GetMapping(path = "/staffId/{staffId}")
    public List<StaffPatientGetDTO> getStaffPatientByStaffId(@PathVariable UUID staffId) throws SQLException {
        return staffPatientService.getStaffPatientByStaffId(staffId);
    }

    @DeleteMapping(path = "{patientId}/{staffId}")
    public void deleteStaffPatientByIds(@PathVariable("patientId") UUID patientId, @PathVariable("staffId") UUID staffId) {
        staffPatientService.deleteStaffPatient(patientId, staffId);
    }
}
