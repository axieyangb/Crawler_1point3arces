package com.jerryxie.forum.worker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jerryxie.forum.worker.domain.SalaryPackage;

public interface SalaryPackageRepository extends MongoRepository<SalaryPackage, String> {
     List<SalaryPackage> findByUpdateTime(long updateTime);
     Optional<SalaryPackage> findByTid(int tid);
}
