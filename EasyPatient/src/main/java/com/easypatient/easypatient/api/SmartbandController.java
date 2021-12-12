package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.SmartbandDTO;
import com.easypatient.easypatient.dto.SmartbandGetDTO;
import com.easypatient.easypatient.service.SmartbandService;
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

@RequestMapping("api/v1/smartband")
@RestController
public class SmartbandController {
    private final SmartbandService smartbandService;

    @Autowired
    public SmartbandController(SmartbandService smartbandService) {
        this.smartbandService = smartbandService;
    }

    @PostMapping
    public void addSmartband(@Valid @NonNull @RequestBody SmartbandDTO smartband) {
        smartbandService.addSmartband(smartband);
    }

    @GetMapping
    public List<SmartbandGetDTO> getAllSmartbands() {
        return smartbandService.getAllSmartbands();
    }

    @GetMapping(path = "{id}")
    public SmartbandGetDTO getSmartbandById(@PathVariable("id") UUID id) {
        return smartbandService.getSmartbandById(id)
                .orElse(null);
    }

    @GetMapping(path = "/getByVariables")
    public List<SmartbandGetDTO> getSmartbandByVariables(@RequestParam(required = false) Optional<String> mac,
                                                         @RequestParam(required = false) Optional<String> name,
                                                         @RequestParam(required = false)
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                     Optional<LocalDateTime> createdAfter,
                                                         @RequestParam(required = false)
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                     Optional<LocalDateTime> updatedAfter) throws SQLException {
        return smartbandService.getSmartbandByVariables(mac,
                                                        name,
                                                        createdAfter,
                                                        updatedAfter);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSmartbandById(@PathVariable("id") UUID id) {
        smartbandService.deleteSmartband(id);
    }

    @PutMapping(path = "{id}")
    public void updateSmartbandById(@PathVariable("id") UUID id,
                                    @RequestParam(required = false) Optional<String> mac,
                                    @RequestParam(required = false)Optional<String> name) throws SQLException {
        smartbandService.updateSmartband(id, mac, name);
    }
}
