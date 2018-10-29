package com.jerryxie.forums;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumApplication {
	private static Logger logger = Logger.getLogger(ForumApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
		if(ForumConstants.OAUTH_VALUE == null) {
			logger.warning("Null Env for OATH_VALUE");
			System.exit(1);
		}
		if(ForumConstants.SALT_VALUE == null) {
			logger.warning("Null Env for SALT_VALUE");
			System.exit(1);
		}
	}
}
