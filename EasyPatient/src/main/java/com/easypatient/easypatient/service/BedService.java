package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.BedDao;
import com.easypatient.easypatient.model.Bed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BedService {
    private final BedDao bedDao;

    @Autowired
    public BedService(@Qualifier("BedPostgres") BedDao bedDao) {
        this.bedDao = bedDao;
    }

    public int addBed(Bed bed) {
        return bedDao.insertBed(bed.getId(), bed);
    }

    public List<Bed> getAllBeds() {
        return bedDao.selectAllBeds();
    }

    public Optional<Bed> getBedById(UUID id) {
        return bedDao.selectBedById(id);
    }

    public int deleteBed(UUID id) {
        return bedDao.deleteBedById(id);
    }

    public int updateBed(UUID id, Bed bed) {
        return bedDao.updateBedById(id, bed);
    }
}
