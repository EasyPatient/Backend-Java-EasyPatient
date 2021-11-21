package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.BedDTO;
import com.easypatient.easypatient.dto.BedGetDTO;
import com.easypatient.easypatient.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/bed")
@RestController
public class BedController {
    private final BedService bedService;

    @Autowired
    public BedController(BedService bedService) {
        this.bedService = bedService;
    }

    @PostMapping
    public void addBed(@Valid @NonNull @RequestBody BedDTO Bed) {
        bedService.addBed(Bed);
    }

    @GetMapping
    public List<BedGetDTO> getAllBeds() {
        return bedService.getAllBeds();
    }

    @GetMapping("{id}")
    public BedGetDTO getBedById(@PathVariable("id") UUID id) {
        return bedService.getBedById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBedById(@PathVariable("id") UUID id) {
        bedService.deleteBed(id);
    }

    @PutMapping(path = "{id}")
    public void updateBedById(@PathVariable("id") UUID id,
                          @RequestParam(required = false) Optional<Integer> number,
                          @RequestParam(required = false) Optional<UUID> patientId,
                          @RequestParam(required = false) Optional<UUID> roomId) throws SQLException {
        bedService.updateBed(id, number, patientId, roomId);
    }

    @GetMapping(path = "/getByVariables")
    public List<BedGetDTO> getBedByVariables(@RequestParam(required = false) Optional<Integer> number,
                                                      @RequestParam(required = false) Optional<UUID> patientId,
                                                      @RequestParam(required = false) Optional<UUID> roomId,
                                                      @RequestParam(required = false) Optional<LocalDateTime> updatedAt,
                                                      @RequestParam(required = false) Optional<LocalDateTime> createdAt) throws SQLException {
        return bedService.getBedByVariables(number,
                patientId,
                roomId,
                createdAt,
                updatedAt);
    }

}
