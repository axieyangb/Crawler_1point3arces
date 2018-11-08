package com.jerryxie.forum.worker.client;

import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.service.SalaryPackageClientService;

import feign.hystrix.FallbackFactory;

@Component
public class SalaryPackageClientFallbackFactory implements FallbackFactory<SalaryPackageClientService> {

    @Override
    public SalaryPackageClientService create(Throwable throwable) {
        return new SalaryPackageClientFallbackService(throwable);
    }

}
