package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.MedicamentsDTO;
import com.easypatient.easypatient.service.MedicamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("api/v1/medicaments")
@RestController
public class MedicamentsController {
    private final MedicamentsService medicamentsService;

    @Autowired
    public MedicamentsController(MedicamentsService medicamentsService) {
        this.medicamentsService = medicamentsService;
    }

    @PostMapping
    public void addMedicaments(@Valid @NonNull @RequestBody MedicamentsDTO medicaments) {
        medicamentsService.addMedicaments(medicaments);
    }









}
