package com.example.calculator.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * SOAP Calculator Request message.
 * Contains two float operands for calculator operations.
 */
@XmlRootElement(name = "CalculatorRequest", namespace = "http://example.com/calculator")
public class CalculatorRequest {
    
    private float a;
    private float b;

    public CalculatorRequest() {
    }

    public CalculatorRequest(float a, float b) {
        this.a = a;
        this.b = b;
    }

    @XmlElement(name = "a", namespace = "http://example.com/calculator")
    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    @XmlElement(name = "b", namespace = "http://example.com/calculator")
    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "CalculatorRequest{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
