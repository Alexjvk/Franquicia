package com.franquicia.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FranchiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FranchiseApplication.class, args);
        System.out.println("Aplicación de franquicias iniciada correctamente.");
    }
}
