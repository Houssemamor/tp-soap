package com.example.calculator.exception;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * SOAP Fault Detail for Calculator exceptions.
 */
@XmlRootElement(name = "CalculatorFault", namespace = "http://example.com/calculator")
public class SoapFaultDetail {
    
    private String faultCode;
    private String message;

    public SoapFaultDetail() {
    }

    public SoapFaultDetail(String faultCode, String message) {
        this.faultCode = faultCode;
        this.message = message;
    }

    @XmlElement(name = "faultCode")
    public String getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }

    @XmlElement(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
