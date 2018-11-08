package com.jerryxie.forum.worker.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerryxie.forum.worker.domain.SalaryPackage;
import com.jerryxie.forum.worker.repository.SalaryPackageRepository;

@Service
public class SalaryPackageService {
    @Autowired
    private SalaryPackageRepository salaryPackageRepository;

    public List<SalaryPackage> findByUpdateTime(long updateTime) {
        return salaryPackageRepository.findByUpdateTime(updateTime);
    }

    public Optional<SalaryPackage> findByTid(int tid) {
        return salaryPackageRepository.findByTid(tid);
    }

    public SalaryPackage insertSalaryPackage(SalaryPackage salaryPackage) {
        return salaryPackageRepository.insert(salaryPackage);
    }

    public Set<Integer> getAllTids() {
        Set<Integer> tidSet = new HashSet<>();
        salaryPackageRepository.findAll().forEach(pack -> {
            tidSet.add(pack.getTid());
        });
        return tidSet;
    }

    public SalaryPackage save(SalaryPackage salaryPackage) {
        return salaryPackageRepository.save(salaryPackage);
    }
}
