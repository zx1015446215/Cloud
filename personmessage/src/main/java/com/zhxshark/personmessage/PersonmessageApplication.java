package com.zhxshark.personmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PersonmessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonmessageApplication.class, args);
    }

}
