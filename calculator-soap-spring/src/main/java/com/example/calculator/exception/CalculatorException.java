package com.example.calculator.exception;

/**
 * Custom exception for calculator operations.
 * Thrown when invalid operations or edge cases occur (e.g., division by zero).
 */
public class CalculatorException extends Exception {
    
    private String faultCode;

    public CalculatorException(String faultCode, String message) {
        super(message);
        this.faultCode = faultCode;
    }

    public String getFaultCode() {
        return faultCode;
    }
}
