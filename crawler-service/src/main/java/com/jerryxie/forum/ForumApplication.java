package com.jerryxie.forum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ForumApplication {
    private static Logger logger = LogManager.getLogger(ForumApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
        if (ForumConstants.OAUTH_VALUE == null) {
            logger.error("Null Env for OATH_VALUE");
            System.exit(1);
        }
        if (ForumConstants.SALT_VALUE == null) {
            logger.error("Null Env for SALT_VALUE");
            System.exit(1);
        }
    }
}
