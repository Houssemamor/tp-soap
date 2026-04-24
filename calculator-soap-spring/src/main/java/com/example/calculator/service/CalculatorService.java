package com.example.calculator.service;

import com.example.calculator.exception.CalculatorException;
import com.example.calculator.model.CalculatorRequest;
import com.example.calculator.model.CalculatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service layer for Calculator operations.
 * Implements arithmetic and temperature conversion logic.
 * Uses Single Responsibility Principle: focuses only on business logic.
 */
@Service
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    // Arithmetic Operations

    /**
     * Addition: a + b
     */
    public CalculatorResponse add(CalculatorRequest request) {
        float result = request.getA() + request.getB();
        logger.debug("Add: {} + {} = {}", request.getA(), request.getB(), result);
        return new CalculatorResponse(result);
    }

    /**
     * Subtraction: a - b
     */
    public CalculatorResponse subtract(CalculatorRequest request) {
        float result = request.getA() - request.getB();
        logger.debug("Subtract: {} - {} = {}", request.getA(), request.getB(), result);
        return new CalculatorResponse(result);
    }

    /**
     * Multiplication: a * b
     */
    public CalculatorResponse multiply(CalculatorRequest request) {
        float result = request.getA() * request.getB();
        logger.debug("Multiply: {} * {} = {}", request.getA(), request.getB(), result);
        return new CalculatorResponse(result);
    }

    /**
     * Division: a / b
     * Throws CalculatorException if b is zero.
     */
    public CalculatorResponse divide(CalculatorRequest request) throws CalculatorException {
        if (request.getB() == 0) {
            logger.error("Division by zero attempted: {} / {}", request.getA(), request.getB());
            throw new CalculatorException("DIVIDE_BY_ZERO", "Division par zéro impossible");
        }
        float result = request.getA() / request.getB();
        logger.debug("Divide: {} / {} = {}", request.getA(), request.getB(), result);
        return new CalculatorResponse(result);
    }

    /**
     * Modulo: a % b (remainder of division)
     * Throws CalculatorException if b is zero.
     */
    public CalculatorResponse modulo(CalculatorRequest request) throws CalculatorException {
        if (request.getB() == 0) {
            logger.error("Modulo by zero attempted: {} % {}", request.getA(), request.getB());
            throw new CalculatorException("MODULO_BY_ZERO", "Modulo par zéro impossible");
        }
        float result = request.getA() % request.getB();
        logger.debug("Modulo: {} % {} = {}", request.getA(), request.getB(), result);
        return new CalculatorResponse(result);
    }

    /**
     * Power: a ^ b (a raised to power b)
     * Throws CalculatorException if a is 0 and b is negative.
     */
    public CalculatorResponse power(CalculatorRequest request) throws CalculatorException {
        if (request.getB() < 0 && request.getA() == 0) {
            logger.error("Invalid power: 0 raised to negative power {}", request.getB());
            throw new CalculatorException("INVALID_POWER", "Impossible de calculer 0 à une puissance négative");
        }
        float result = (float) Math.pow(request.getA(), request.getB());
        logger.debug("Power: {} ^ {} = {}", request.getA(), request.getB(), result);
        return new CalculatorResponse(result);
    }

    // Temperature Conversions

    /**
     * Celsius to Fahrenheit: F = (C * 9/5) + 32
     * Uses parameter 'a' as input (celsius value)
     */
    public CalculatorResponse celsiusToFahrenheit(CalculatorRequest request) {
        float celsius = request.getA();
        float fahrenheit = (celsius * 9 / 5) + 32;
        logger.debug("CelsiusToFahrenheit: {}°C = {}°F", celsius, fahrenheit);
        return new CalculatorResponse(fahrenheit);
    }

    /**
     * Fahrenheit to Celsius: C = (F - 32) * 5/9
     * Uses parameter 'a' as input (fahrenheit value)
     */
    public CalculatorResponse fahrenheitToCelsius(CalculatorRequest request) {
        float fahrenheit = request.getA();
        float celsius = (fahrenheit - 32) * 5 / 9;
        logger.debug("FahrenheitToCelsius: {}°F = {}°C", fahrenheit, celsius);
        return new CalculatorResponse(celsius);
    }

    /**
     * Celsius to Kelvin: K = C + 273.15
     * Uses parameter 'a' as input (celsius value)
     */
    public CalculatorResponse celsiusToKelvin(CalculatorRequest request) {
        float celsius = request.getA();
        float kelvin = celsius + 273.15f;
        logger.debug("CelsiusToKelvin: {}°C = {}K", celsius, kelvin);
        return new CalculatorResponse(kelvin);
    }
}
