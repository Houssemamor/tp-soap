package com.example.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application class for Calculator SOAP Service.
 * Starts the SOAP web service on port 8080.
 * WSDL available at: http://localhost:8080/ws/calculator.wsdl
 */
@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
        System.out.println("✅ Calculator SOAP Service started");
        System.out.println("📍 WSDL: http://localhost:8080/ws/calculator.wsdl");
    }
}
