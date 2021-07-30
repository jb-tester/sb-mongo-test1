package com.mytests.springboot.sbmongotest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbMongoTest1Application implements CommandLineRunner {
     @Autowired
    EmployeeService service;
    public static void main(String[] args) {
        SpringApplication.run(SbMongoTest1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       service.setUpDB(); 
       service.displayAll();
    }
}
