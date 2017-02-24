package com.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

/**
 * @author Jonsy
 *
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableAdminServer
public class Application {

    public static void main(String[] arg){
        SpringApplication.run(Application.class);
    }
}

