package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Smartband;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("SmartbandPostgres")
public class SmartbandDataBaseAccessService implements SmartbandDao {
    @Override
    public void insertSmartband(UUID id, Smartband smartband) {
    }

    @Override
    public List<Smartband> selectAllSmartbands() {
        return List.of();
    }

    @Override
    public void deleteSmartbandById(UUID id) {
    }

    @Override
    public void updateSmartbandById(UUID id, Smartband smartband) {
    }

    @Override
    public Optional<Smartband> selectSmartbandById(UUID id) {
        return Optional.ofNullable(Smartband.builder().build());
    }
}
