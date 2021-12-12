package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.MedicamentsDTO;
import com.easypatient.easypatient.dto.MedicamentsGetDTO;
import com.easypatient.easypatient.service.MedicamentsService;
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

    @GetMapping
    public List<MedicamentsGetDTO> getAllMedicaments() {
        return medicamentsService.getAllMedicaments();
    }

    @GetMapping(path = "{id}")
    public MedicamentsGetDTO getMedicamentsById(@PathVariable("id") UUID id) {
        return medicamentsService.getMedicamentsById(id).orElse(null);
    }

    @GetMapping(path = "/getByVariables")
    public List<MedicamentsGetDTO> getMedicamentsByVariables(@RequestParam(required = false) Optional<String> name,
                                                             @RequestParam(required = false) Optional<String> type,
                                                             @RequestParam(required = false) Optional<String> value,
                                                             @RequestParam(required = false)
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                         Optional<LocalDateTime> createdAfter,
                                                             @RequestParam(required = false)
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                                         Optional<LocalDateTime> updatedAfter) throws SQLException {
        return medicamentsService.getMedicamentsByVariables(name, type, value, createdAfter, updatedAfter);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMedicamentsById(@PathVariable("id") UUID id) {
        medicamentsService.deleteMedicaments(id);
    }

    @PutMapping(path = "{id}")
    public void updateMedicamentsById(@PathVariable("id") UUID id,
                                      @RequestParam(required = false) Optional<String> name,
                                      @RequestParam(required = false) Optional<String> type,
                                      @RequestParam(required = false) Optional<String> value) throws SQLException {
        medicamentsService.updateMedicaments(id, name, type, value);
    }
}
