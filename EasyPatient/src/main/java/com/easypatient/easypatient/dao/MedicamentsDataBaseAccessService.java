package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.MedicamentsDTO;
import com.easypatient.easypatient.dto.MedicamentsGetDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.model.Medicaments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MedicamentsPostgres")
public class MedicamentsDataBaseAccessService implements MedicamentsDao{

    final String sqlSelectAllMedicaments = "SELECT id, name, type, value, created_at, updated_at FROM medicaments";
    final String sqlSelectMedicamentsByID = "SELECT id, name, type, value, created_at, updated_at FROM medicaments WHERE id = ?";
    final String sqlInsertMedicaments = "INSERT INTO medicaments VALUES(?, ?, ?, ?, ?)";


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicamentsDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static MedicamentsGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String type = resultSet.getString("type");
        String value = resultSet.getString("value");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return MedicamentsGetDTO.builder()
                .id(id)
                .name(name)
                .type(type)
                .value(value)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
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
        return jdbcTemplate.query(sqlSelectAllMedicaments,
                MedicamentsDataBaseAccessService::mapRow);
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
        MedicamentsGetDTO medicaments = jdbcTemplate.queryForObject(
                sqlSelectMedicamentsByID,
                new Object[]{id},
                MedicamentsDataBaseAccessService::mapRow);
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
