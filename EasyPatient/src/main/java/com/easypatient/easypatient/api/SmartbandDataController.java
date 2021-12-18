package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.dto.SmartbandDataDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;
import com.easypatient.easypatient.service.SmartbandDataService;
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

@RequestMapping("api/v1/smartbandData")
@RestController
public class SmartbandDataController {
    private final SmartbandDataService smartbandDataService;

    @Autowired
    public SmartbandDataController(SmartbandDataService smartbandDataService) {
        this.smartbandDataService = smartbandDataService;
    }

    @PostMapping
    public void addSmartbandData(@Valid @NonNull @RequestBody SmartbandDataDTO smartbandData) throws SQLException {
        smartbandDataService.addSmartbandData(smartbandData);
    }

    @GetMapping
    public List<SmartbandDataGetDTO> getAllSmartbandData() {
        return smartbandDataService.getAllSmartbandData();
    }

    @GetMapping(path = "/smartbandId/{smartbandId}")
    public List<SmartbandDataGetDTO> getSmartbandDataBySmartbandId(@PathVariable("smartbandId") UUID smartbandId) throws SQLException {
        return smartbandDataService.getSmartbandDataBySmartbandId(smartbandId);
    }

    @GetMapping(path = "/patientId/{patientId}")
    public List<SmartbandDataGetDTO> getSmartbandDataByPatientId(@PathVariable("patientId") UUID patientId) throws SQLException {
        return smartbandDataService.getSmartbandDataByPatientId(patientId);
    }

    @DeleteMapping(path = "{patientId}/{smartbandId}")
    public void deleteSmartbandDataByIds(@PathVariable("patientId") UUID patientId, @PathVariable("smartbandId") UUID smartbandId) {
        smartbandDataService.deleteSmartbandData(patientId, smartbandId);
    }

    @GetMapping(path = "/getByVariables")
    public List<SmartbandDataGetDTO> getSmartbandDataByVariables(@RequestParam(required = false) Optional<UUID> smartbandId,
                                                      @RequestParam(required = false) Optional<UUID> patientId,
                                                      @RequestParam(required = false) Optional<String> heartRate,
                                                      @RequestParam(required = false) Optional<String> oxygen,
                                                      @RequestParam(required = false) Optional<String> temperature,
                                                      @RequestParam(required = false) Optional<String> battery,
                                                                 @RequestParam(required = false)
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                             Optional<LocalDateTime> createdAfter,
                                                                 @RequestParam(required = false)
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                             Optional<LocalDateTime> updatedAfter) throws SQLException {
        return smartbandDataService.getSmartbandDataByVariables(smartbandId,
                patientId,
                heartRate,
                oxygen,
                temperature,
                battery,
                createdAfter,
                updatedAfter);
    }


}
