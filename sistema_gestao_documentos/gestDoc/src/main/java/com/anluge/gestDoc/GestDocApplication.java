package com.anluge.gestDoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GestDocApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestDocApplication.class, args);
    }
}