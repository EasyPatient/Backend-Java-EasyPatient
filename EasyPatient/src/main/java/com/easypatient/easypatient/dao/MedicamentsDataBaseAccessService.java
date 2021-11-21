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
        int i = 0;
        int k = 0;
        final String sqlSelectMedicamentsByVariable = "SELECT name, type, value, created_at, updated_at FROM medicaments WHERE ";
        final String sqlName = " (name = ?)";
        final String sqlType = " (type = ?)";
        final String sqlValue = " (value = ?)";
        final String sqlCreatedAt = " (created_at = ?)";
        final String sqlUpdatedAt = " (updated_at = ?)";
        final String sqlAnd = " AND ";
        final String sqlSemicolon = ";";

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectMedicamentsByVariable));

        if(name.isPresent()) {
            i++;
            expressions.add(sqlName);
        }
        if(type.isPresent()) {
            i++;
            expressions.add(sqlType);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(value.isPresent()) {
            i++;
            expressions.add(sqlValue);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(createdAt.isPresent()) {
            i++;
            expressions.add(sqlCreatedAt);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(updatedAt.isPresent()) {
            i++;
            expressions.add(sqlUpdatedAt);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }

        expressions.add(sqlSemicolon);
        String sqlExpression = String.join(" ", expressions);

        if(i != 0) {
            Object[] jdbcTable = new Object[i];
            if(name.isPresent()) {
                jdbcTable[k] = name.get();
                k++;
            }
            if(type.isPresent()) {
                jdbcTable[k] = type.get();
                k++;
            }
            if(value.isPresent()) {
                jdbcTable[k] = value.get();
                k++;
            }
            if(createdAt.isPresent()) {
                jdbcTable[k] = createdAt.get();
                k++;
            }
            if(updatedAt.isPresent()) {
                jdbcTable[k] = updatedAt.get();
                k++;
            }
            return jdbcTemplate.query(
                    sqlExpression,
                    jdbcTable,
                    MedicamentsDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }
    }

}
