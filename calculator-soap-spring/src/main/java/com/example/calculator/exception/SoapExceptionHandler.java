package com.example.calculator.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Exception handler for SOAP operations.
 * Spring-WS automatically converts thrown exceptions to SOAP Faults.
 * This component provides logging and error tracking for exceptions.
 */
@Component
public class SoapExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(SoapExceptionHandler.class);
    
    /**
     * Log and return fault detail for CalculatorException.
     * When a CalculatorException is thrown from an endpoint,
     * Spring-WS will automatically convert it to a SOAP Fault.
     */
    public SoapFaultDetail handleCalculatorException(CalculatorException ex) {
        logger.error("Calculator operation failed: {} - {}", ex.getFaultCode(), ex.getMessage());
        SoapFaultDetail detail = new SoapFaultDetail();
        detail.setFaultCode(ex.getFaultCode());
        detail.setMessage(ex.getMessage());
        return detail;
    }
}
