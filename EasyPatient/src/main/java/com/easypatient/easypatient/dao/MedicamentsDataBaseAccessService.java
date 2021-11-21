package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.MedicamentsDTO;
import com.easypatient.easypatient.dto.MedicamentsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MedicamentsPostgres")
public class MedicamentsDataBaseAccessService implements MedicamentsDao{

    final String sqlInsertMedicaments = "INSERT INTO medicaments VALUES(?, ?, ?, ?, ?)";


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicamentsDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertMedicaments(MedicamentsDTO medicaments) {
        LocalDateTime date = LocalDateTime.now();

        jdbcTemplate.update(sqlInsertMedicaments,
                medicaments.getName(),
                medicaments.getType(),
                medicaments.getValue(),
                date,
                date);
    }

    @Override
    public List<MedicamentsGetDTO> selectAllMedicaments() {
        return List.of();
    }

    @Override
    public void deleteMedicamentsById(UUID id) {
    }

    @Override
    public void updateMedicamentsById(UUID id,
                                      Optional<String> name,
                                      Optional<String> type,
                                      Optional<String> value) throws SQLException {
    }

    @Override
    public Optional<MedicamentsGetDTO> selectMedicamentsById(UUID id) {
        return Optional.ofNullable(MedicamentsGetDTO.builder().build());
    }

    @Override
    public List<MedicamentsGetDTO> selectMedicamentsByVariables(Optional<String> name,
                                                                Optional<String> type,
                                                                Optional<String> value,
                                                                Optional<LocalDateTime> createdAt,
                                                                Optional<LocalDateTime> updatedAt) throws SQLException {
        return List.of();
    }

}
