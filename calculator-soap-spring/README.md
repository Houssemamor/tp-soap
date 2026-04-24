# Calculator SOAP Spring Boot Service

A Spring Boot implementation of a SOAP-based Calculator Service with 9 operations:
- Arithmetic: Add, Subtract, Multiply, Divide, Modulo, Power
- Temperature: CelsiusToFahrenheit, FahrenheitToCelsius, CelsiusToKelvin

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher

## Project Structure

```
calculator-soap-spring/
├── pom.xml
├── src/main/
│   ├── java/com/example/calculator/
│   │   ├── CalculatorApplication.java
│   │   ├── config/
│   │   │   └── WebServiceConfig.java
│   │   ├── endpoint/
│   │   │   └── CalculatorEndpoint.java
│   │   ├── service/
│   │   │   └── CalculatorService.java
│   │   ├── model/
│   │   │   ├── CalculatorRequest.java
│   │   │   └── CalculatorResponse.java
│   │   └── exception/
│   │       ├── CalculatorException.java
│   │       ├── SoapExceptionHandler.java
│   │       └── SoapFaultDetail.java
│   └── resources/
│       ├── application.properties
│       └── wsdl/
│           └── calculator.wsdl
└── README.md
```

## Building the Project

```bash
mvn clean install
```

This will:
1. Download all dependencies
2. Compile Java source files
3. Generate JAXB classes from WSDL (optional)
4. Create an executable JAR file in the `target/` directory

## Running the Service

```bash
mvn spring-boot:run
```

Or run the JAR directly:

```bash
java -jar target/calculator-soap-spring-1.0.0.jar
```

The service will start on **http://localhost:8080**

## Accessing the Service

**WSDL Document:**
```
http://localhost:8080/ws/calculator.wsdl
```

**SOAP Endpoint:**
```
http://localhost:8080/ws/calculator
```

## API Operations

### Arithmetic Operations

#### Add
```xml
<CalculatorRequest>
  <a>15</a>
  <b>7</b>
</CalculatorRequest>
```
Response: 22.0

#### Subtract
```xml
<CalculatorRequest>
  <a>15</a>
  <b>7</b>
</CalculatorRequest>
```
Response: 8.0

#### Multiply
```xml
<CalculatorRequest>
  <a>4</a>
  <b>7</b>
</CalculatorRequest>
```
Response: 28.0

#### Divide
```xml
<CalculatorRequest>
  <a>20</a>
  <b>4</b>
</CalculatorRequest>
```
Response: 5.0
**Error handling:** Throws SOAP Fault if b=0

#### Modulo
```xml
<CalculatorRequest>
  <a>17</a>
  <b>5</b>
</CalculatorRequest>
```
Response: 2.0

#### Power
```xml
<CalculatorRequest>
  <a>2</a>
  <b>8</b>
</CalculatorRequest>
```
Response: 256.0

### Temperature Conversions

#### CelsiusToFahrenheit
```xml
<CalculatorRequest>
  <a>0</a>
  <b>0</b>
</CalculatorRequest>
```
Response: 32.0

#### FahrenheitToCelsius
```xml
<CalculatorRequest>
  <a>32</a>
  <b>0</b>
</CalculatorRequest>
```
Response: 0.0

#### CelsiusToKelvin
```xml
<CalculatorRequest>
  <a>0</a>
  <b>0</b>
</CalculatorRequest>
```
Response: 273.15

## Testing with Postman

1. Create a new POST request
2. Set URL to: `http://localhost:8080/ws/calculator`
3. Add headers:
   - `Content-Type: text/xml`
   - `SOAPAction: Add` (change based on operation)
4. Set body (raw XML):

```xml
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
 xmlns:tns="http://example.com/calculator">
 <soap:Body>
 <tns:CalculatorRequest>
 <tns:a>15</tns:a>
 <tns:b>7</tns:b>
 </tns:CalculatorRequest>
 </soap:Body>
</soap:Envelope>
```

## Architecture

**Design Patterns Used:**
- **Service Layer Pattern**: Business logic isolated in `CalculatorService`
- **Endpoint Pattern**: SOAP endpoint mapping via Spring-WS annotations
- **Exception Handling**: Centralized error handling with SOAP Faults
- **Dependency Injection**: Spring manages component lifecycle and wiring

**SOLID Principles:**
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Extended via new operations without modifying existing code
- **Dependency Inversion**: Depends on abstraction (service interface pattern)

## Configuration

Edit `application.properties` to modify:
```properties
server.port=8080                              # Change port
logging.level.com.example.calculator=DEBUG    # Adjust logging
```

## Error Handling

**Division by Zero:**
```xml
<SOAP-ENV:Fault>
  <faultcode>DIVIDE_BY_ZERO</faultcode>
  <faultstring>Division par zéro impossible</faultstring>
</SOAP-ENV:Fault>
```

**Invalid Power Operation:**
```xml
<SOAP-ENV:Fault>
  <faultcode>INVALID_POWER</faultcode>
  <faultstring>Impossible de calculer 0 à une puissance négative</faultstring>
</SOAP-ENV:Fault>
```

## Troubleshooting

**Port already in use:**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"
```

**WSDL not loading:**
- Verify `calculator.wsdl` is in `src/main/resources/wsdl/`
- Check `WebServiceConfig` bean is properly registered

**SOAP requests failing:**
- Ensure namespace is correct: `http://example.com/calculator`
- Verify request structure matches WSDL definition

## License

ISC
