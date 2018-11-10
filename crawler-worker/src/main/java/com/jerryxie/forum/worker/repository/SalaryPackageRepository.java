package com.jerryxie.forum.worker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jerryxie.forum.worker.domain.SalaryPackage;
@Repository
public interface SalaryPackageRepository extends MongoRepository<SalaryPackage, String> {
     List<SalaryPackage> findByUpdateTime(long updateTime);
     Optional<SalaryPackage> findByTid(int tid);
}
