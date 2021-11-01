package com.easypatient.easypatient.api;

import com.easypatient.easypatient.model.Bed;
import com.easypatient.easypatient.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    public void addBed(@Valid @NonNull @RequestBody Bed Bed) {
        bedService.addBed(Bed);
    }

    @GetMapping
    public List<Bed> getAllPeople() {
        return bedService.getAllBeds();
    }

    @GetMapping("{id}")
    public Bed getBedById(@PathVariable("id") UUID id) {
        return bedService.getBedById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBedById(@PathVariable("id") UUID id) {
        bedService.deleteBed(id);
    }

    @PutMapping(path = "{id}")
    public void updateBed(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Bed BedToUpdate) {
        bedService.updateBed(id, BedToUpdate);
    }
}
