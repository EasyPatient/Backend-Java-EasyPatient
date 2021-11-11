package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.SmartbandData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("SmartbandDataPostgres")
public class SmartbandDataDataBaseAccessService implements SmartbandDataDao {
    @Override
    public void insertSmartbandData(UUID id, SmartbandData smartbandData) {
    }

    @Override
    public List<SmartbandData> selectAllSmartbandDatas() {
        return List.of();
    }

    @Override
    public void deleteSmartbandDataById(UUID id) {
    }

    @Override
    public void updateSmartbandDataById(UUID id, SmartbandData smartbandData) {
    }

    @Override
    public Optional<SmartbandData> selectSmartbandDataById(UUID id) {
        return Optional.ofNullable(SmartbandData.builder().build());
    }
}
