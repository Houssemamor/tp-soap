package com.example.calculator;

import com.example.calculator.model.CalculatorRequest;
import com.example.calculator.model.CalculatorResponse;
import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for CalculatorService.
 * Tests arithmetic and temperature conversion operations.
 * Uses simple unit tests without Spring Boot context to avoid context loading issues.
 */
class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        // Create service instance directly without Spring context
        calculatorService = new CalculatorService();
    }

    // Arithmetic Tests

    @Test
    void testAdd() {
        CalculatorRequest request = new CalculatorRequest(10, 5);
        CalculatorResponse response = calculatorService.add(request);
        assertEquals(15.0f, response.getResult());
    }

    @Test
    void testSubtract() {
        CalculatorRequest request = new CalculatorRequest(10, 3);
        CalculatorResponse response = calculatorService.subtract(request);
        assertEquals(7.0f, response.getResult());
    }

    @Test
    void testMultiply() {
        CalculatorRequest request = new CalculatorRequest(4, 7);
        CalculatorResponse response = calculatorService.multiply(request);
        assertEquals(28.0f, response.getResult());
    }

    @Test
    void testDivide() throws Exception {
        CalculatorRequest request = new CalculatorRequest(20, 4);
        CalculatorResponse response = calculatorService.divide(request);
        assertEquals(5.0f, response.getResult());
    }

    @Test
    void testDivideByZero() {
        CalculatorRequest request = new CalculatorRequest(10, 0);
        assertThrows(Exception.class, () -> calculatorService.divide(request));
    }

    @Test
    void testModulo() throws Exception {
        CalculatorRequest request = new CalculatorRequest(17, 5);
        CalculatorResponse response = calculatorService.modulo(request);
        assertEquals(2.0f, response.getResult());
    }

    @Test
    void testPower() throws Exception {
        CalculatorRequest request = new CalculatorRequest(2, 8);
        CalculatorResponse response = calculatorService.power(request);
        assertEquals(256.0f, response.getResult());
    }

    // Temperature Conversion Tests

    @Test
    void testCelsiusToFahrenheit() {
        CalculatorRequest request = new CalculatorRequest(0, 0);
        CalculatorResponse response = calculatorService.celsiusToFahrenheit(request);
        assertEquals(32.0f, response.getResult());
    }

    @Test
    void testFahrenheitToCelsius() {
        CalculatorRequest request = new CalculatorRequest(32, 0);
        CalculatorResponse response = calculatorService.fahrenheitToCelsius(request);
        assertEquals(0.0f, response.getResult(), 0.01f);
    }

    @Test
    void testCelsiusToKelvin() {
        CalculatorRequest request = new CalculatorRequest(0, 0);
        CalculatorResponse response = calculatorService.celsiusToKelvin(request);
        assertEquals(273.15f, response.getResult());
    }
}
