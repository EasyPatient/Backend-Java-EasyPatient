package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.MedicamentsDTO;
import com.easypatient.easypatient.dto.MedicamentsGetDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicamentsDao {
    void insertMedicaments(MedicamentsDTO medicaments);

    List<MedicamentsGetDTO> selectAllMedicaments();

    void deleteMedicamentsById(UUID id);

    void updateMedicamentsById(UUID id,
                               Optional<String> name,
                               Optional<String> type,
                               Optional<String> value) throws SQLException;

    Optional<MedicamentsGetDTO> selectMedicamentsById(UUID id);

    List<MedicamentsGetDTO> selectMedicamentsByVariables(Optional<String> name,
                                      Optional<String> type,
                                      Optional<String> value,
                                      Optional<LocalDateTime> createdAt,
                                      Optional<LocalDateTime> updatedAt) throws SQLException;
}
