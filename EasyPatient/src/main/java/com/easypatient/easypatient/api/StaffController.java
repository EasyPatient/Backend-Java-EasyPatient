package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.StaffDTO;
import com.easypatient.easypatient.dto.StaffGetDTO;
import com.easypatient.easypatient.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/staff")
@RestController
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public void addStaff(@Valid @NonNull @RequestBody StaffDTO staff) {
        staffService.addStaff(staff);
    }

    @GetMapping
    public List<StaffGetDTO> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping(path = "{id}")
    public StaffGetDTO getStaffById(@PathVariable("id") UUID id) {
        return staffService.getStaffById(id)
                .orElse(null);
    }

    @GetMapping(path = "/getByVariables")
    public List<StaffGetDTO> getStaffByVariables(@RequestParam(required = false) Optional<String> name,
                                                 @RequestParam(required = false) Optional<String> email,
                                                 @RequestParam(required = false) Optional<String> phone,
                                                 @RequestParam(required = false) Optional<String> phoneAreaCode,
                                                 @RequestParam(required = false) Optional<String> password,
                                                 @RequestParam(required = false) Optional<String> role,
                                                 @RequestParam(required = false) Optional<LocalDateTime> createdAt,
                                                 @RequestParam(required = false) Optional<LocalDateTime> updatedAt) throws SQLException {
        return staffService.getStaffByVariables(name,
                email,
                phone,
                phoneAreaCode,
                password,
                role,
                createdAt,
                updatedAt);
    }

    @DeleteMapping(path = "{id}")
    public void daleteStaffById(@PathVariable("id") UUID id) {
        staffService.deleteStaff(id);
    }

    @PutMapping(path = "{id}")
    public void updateStaffById(@PathVariable("id") UUID id,
                                @RequestParam(required = false) Optional<String> name,
                                @RequestParam(required = false) Optional<String> email,
                                @RequestParam(required = false) Optional<String> phone,
                                @RequestParam(required = false) Optional<String> phoneAreaCode,
                                @RequestParam(required = false) Optional<String> password,
                                @RequestParam(required = false) Optional<String> role) throws SQLException {
        staffService.updateStaff(id,
                name,
                email,
                phone,
                phoneAreaCode,
                password,
                role);
    }

}
