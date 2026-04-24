package com.example.calculator.endpoint;

import com.example.calculator.exception.CalculatorException;
import com.example.calculator.model.CalculatorRequest;
import com.example.calculator.model.CalculatorResponse;
import com.example.calculator.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapMessage;

/**
 * SOAP Endpoint for Calculator Service.
 * Routes operations based on SOAPAction extracted from SOAP message.
 * MessageContext is injected per-request as a method parameter.
 */
@Endpoint
public class CalculatorEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorEndpoint.class);
    private static final String NAMESPACE_URI = "http://example.com/calculator";

    @Autowired
    private CalculatorService calculatorService;

    /**
     * Handles all calculator requests.
     * Routes to appropriate operation based on SOAPAction.
     * MessageContext is provided as a method parameter by Spring-WS.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CalculatorRequest")
    @ResponsePayload
    public CalculatorResponse handleCalculatorRequest(
            @RequestPayload CalculatorRequest request,
            MessageContext messageContext) throws CalculatorException {
        
        // Extract SOAPAction from SOAP message
        String soapAction = extractSoapAction(messageContext);
        logger.info("SOAP Operation: {} - a={}, b={}", soapAction, request.getA(), request.getB());
        
        // Route to appropriate operation based on SOAPAction
        return switch (soapAction) {
            case "Add" -> calculatorService.add(request);
            case "Subtract" -> calculatorService.subtract(request);
            case "Multiply" -> calculatorService.multiply(request);
            case "Divide" -> calculatorService.divide(request);
            case "Modulo" -> calculatorService.modulo(request);
            case "Power" -> calculatorService.power(request);
            case "CelsiusToFahrenheit" -> calculatorService.celsiusToFahrenheit(request);
            case "FahrenheitToCelsius" -> calculatorService.fahrenheitToCelsius(request);
            case "CelsiusToKelvin" -> calculatorService.celsiusToKelvin(request);
            default -> {
                logger.warn("Unknown operation: {}", soapAction);
                throw new IllegalArgumentException("Unknown operation: " + soapAction);
            }
        };
    }

    /**
     * Extracts SOAPAction from the SOAP message or throws if not found.
     */
    private String extractSoapAction(MessageContext messageContext) {
        Object requestMessage = messageContext.getRequest();
        
        if (requestMessage instanceof SoapMessage soapMessage) {
            String soapAction = soapMessage.getSoapAction();
            if (soapAction != null && !soapAction.isEmpty()) {
                // Normalize: remove quotes and leading '#' if present
                soapAction = soapAction.trim();
                if (soapAction.startsWith("\"") && soapAction.endsWith("\"")) {
                    soapAction = soapAction.substring(1, soapAction.length() - 1);
                }
                if (soapAction.startsWith("#")) {
                    soapAction = soapAction.substring(1);
                }
                return soapAction;
            }
        }
        
        throw new IllegalStateException("SOAPAction header is missing or could not be extracted from SOAP message");
    }
}
