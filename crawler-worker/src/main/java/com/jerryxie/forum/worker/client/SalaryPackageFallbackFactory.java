package com.jerryxie.forum.worker.client;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class SalaryPackageFallbackFactory implements FallbackFactory<SalaryPackageService> {

    @Override
    public SalaryPackageService create(Throwable throwable) {
        return new SalaryPackageFallbackService(throwable);
    }

}
