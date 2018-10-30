package com.jerryxie.forum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jerryxie.forum.doubaofu.PackageInfoFetcher;

@SpringBootApplication
public class ForumApplication {
    private static Logger logger = Logger
            .getLogger(
                    ForumApplication.class);
    @Autowired
    private static PackageInfoFetcher fetcher;

    public static void main(
            String[] args) {
        SpringApplication.run(
                ForumApplication.class,
                args);
        if (ForumConstants.OAUTH_VALUE == null) {
            logger.error(
                    "Null Env for OATH_VALUE");
            System.exit(1);
        }
        if (ForumConstants.SALT_VALUE == null) {
            logger.error(
                    "Null Env for SALT_VALUE");
            System.exit(1);
        }

        System.out.println(fetcher
                .getBase(451958));
    }
}
