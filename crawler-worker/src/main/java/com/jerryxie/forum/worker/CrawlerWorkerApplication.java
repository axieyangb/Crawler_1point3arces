package com.jerryxie.forum.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class CrawlerWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerWorkerApplication.class, args).getBean(WorkerInitializer.class).initWorkerToFetchSalaryTidList();

    }

}
