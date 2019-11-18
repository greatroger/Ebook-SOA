package com.cloud.clouddiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudDiscoveryApplication {

    public static void main(String[] args) {

        SpringApplication.run(CloudDiscoveryApplication.class, args);
    }

}
