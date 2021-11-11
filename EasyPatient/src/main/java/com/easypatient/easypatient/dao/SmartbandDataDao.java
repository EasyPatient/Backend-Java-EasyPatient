package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.SmartbandData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SmartbandDataDao {
    void insertSmartbandData(UUID id, SmartbandData smartbandData);

    List<SmartbandData> selectAllSmartbandDatas();

    void deleteSmartbandDataById(UUID id);

    void updateSmartbandDataById(UUID id, SmartbandData smartbandData);

    Optional<SmartbandData> selectSmartbandDataById(UUID id);
}
