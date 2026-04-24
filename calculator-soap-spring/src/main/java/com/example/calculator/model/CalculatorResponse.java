package com.example.calculator.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * SOAP Calculator Response message.
 * Contains the result of a calculator operation.
 */
@XmlRootElement(name = "CalculatorResponse", namespace = "http://example.com/calculator")
public class CalculatorResponse {
    
    private float result;

    public CalculatorResponse() {
    }

    public CalculatorResponse(float result) {
        this.result = result;
    }

    @XmlElement(name = "result", namespace = "http://example.com/calculator")
    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CalculatorResponse{" +
                "result=" + result +
                '}';
    }
}
