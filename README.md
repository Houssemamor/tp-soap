# SOAP Calculator Services

This repository contains two implementations of a SOAP-based calculator service:
1. **Node.js/Express** - Original implementation
2. **Spring Boot** - Java microservice port

Both services provide identical SOAP endpoints for arithmetic operations, advanced math, and temperature conversions.

## Supported Operations

### Arithmetic Operations
- **Add**: a + b
- **Subtract**: a - b
- **Multiply**: a × b
- **Divide**: a ÷ b (with zero-division error handling)
- **Modulo**: a % b (with zero-division error handling)
- **Power**: a ^ b (with special case handling for 0^negative)

### Temperature Conversions
- **CelsiusToFahrenheit**: C × (9/5) + 32
- **FahrenheitToCelsius**: (F - 32) × (5/9)
- **CelsiusToKelvin**: C + 273.15

---

## Node.js Implementation

### Setup

```bash
cd /path/to/tp-soap
npm install
```

### Start Server

```bash
node server.js
```

The server listens on **http://localhost:3000** and serves the WSDL at **http://localhost:3000/calculator.wsdl**.

### Test with cURL

All operations use the same SOAP endpoint. Route to appropriate operation using the **SOAPAction** HTTP header.

#### Add: 10 + 5 = 15
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Add" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>10</tns:a><tns:b>5</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Subtract: 20 - 8 = 12
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Subtract" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>20</tns:a><tns:b>8</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Multiply: 4 × 7 = 28
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Multiply" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>4</tns:a><tns:b>7</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Divide: 20 ÷ 4 = 5
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Divide" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>20</tns:a><tns:b>4</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Modulo: 17 % 5 = 2
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Modulo" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>17</tns:a><tns:b>5</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Power: 2 ^ 8 = 256
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Power" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>2</tns:a><tns:b>8</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### CelsiusToFahrenheit: 0°C = 32°F
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: CelsiusToFahrenheit" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>0</tns:a><tns:b>0</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### FahrenheitToCelsius: 32°F = 0°C
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: FahrenheitToCelsius" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>32</tns:a><tns:b>0</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### CelsiusToKelvin: 0°C = 273.15K
```bash
curl -X POST http://localhost:3000/soap \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: CelsiusToKelvin" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>0</tns:a><tns:b>0</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

### Test with Postman

1. **Open Postman** and create a new request
2. **Method**: POST
3. **URL**: http://localhost:3000/soap
4. **Headers tab**: 
   - Content-Type: `text/xml`
   - SOAPAction: `Add` (or any other operation: Subtract, Multiply, Divide, Modulo, Power, CelsiusToFahrenheit, etc.)
5. **Body tab** → Raw (XML):
   ```xml
   <?xml version="1.0"?>
   <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator">
     <soap:Body>
       <tns:CalculatorRequest>
         <tns:a>10</tns:a>
         <tns:b>5</tns:b>
       </tns:CalculatorRequest>
     </soap:Body>
   </soap:Envelope>
   ```
6. **Send** and view the response

---

## Spring Boot Implementation

### Setup

```bash
cd /path/to/tp-soap/calculator-soap-spring
./mvnw clean install
```

Requires Java 17+.

### Start Server

```bash
./mvnw spring-boot:run
```

The server listens on **http://localhost:8080** and serves the WSDL at **http://localhost:8080/ws/calculator.wsdl**.

### Test with cURL

All requests go to the `/ws/calculator` endpoint. Route operations using the **SOAPAction** HTTP header.

#### Add: 10 + 5 = 15
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Add" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>10</tns:a><tns:b>5</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Subtract: 20 - 8 = 12
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Subtract" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>20</tns:a><tns:b>8</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Multiply: 4 × 7 = 28
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Multiply" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>4</tns:a><tns:b>7</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Divide: 20 ÷ 4 = 5
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Divide" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>20</tns:a><tns:b>4</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Modulo: 17 % 5 = 2
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Modulo" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>17</tns:a><tns:b>5</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### Power: 2 ^ 8 = 256
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Power" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>2</tns:a><tns:b>8</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### CelsiusToFahrenheit: 0°C = 32°F
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: CelsiusToFahrenheit" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>0</tns:a><tns:b>0</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### FahrenheitToCelsius: 32°F = 0°C
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: FahrenheitToCelsius" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>32</tns:a><tns:b>0</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

#### CelsiusToKelvin: 0°C = 273.15K
```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: CelsiusToKelvin" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>0</tns:a><tns:b>0</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

### Test with Postman

1. **Open Postman** and create a new request
2. **Method**: POST
3. **URL**: http://localhost:8080/ws/calculator
4. **Headers tab**: 
   - Content-Type: `text/xml`
   - SOAPAction: `Add` (or any other operation)
5. **Body tab** → Raw (XML):
   ```xml
   <?xml version="1.0"?>
   <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator">
     <soap:Body>
       <tns:CalculatorRequest>
         <tns:a>10</tns:a>
         <tns:b>5</tns:b>
       </tns:CalculatorRequest>
     </soap:Body>
   </soap:Envelope>
   ```
6. **Send** and view the response

### Test Error Handling (Division by Zero)

```bash
curl -X POST http://localhost:8080/ws/calculator \
  -H "Content-Type: text/xml" \
  -H "SOAPAction: Divide" \
  -d '<?xml version="1.0"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://example.com/calculator"><soap:Body><tns:CalculatorRequest><tns:a>10</tns:a><tns:b>0</tns:b></tns:CalculatorRequest></soap:Body></soap:Envelope>'
```

Returns a SOAP fault with `faultCode: DIVIDE_BY_ZERO`.

---

## Response Format

Both implementations return responses in SOAP envelope format:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Body>
    <ns3:CalculatorResponse xmlns:ns3="http://example.com/calculator">
      <result>15.0</result>
    </ns3:CalculatorResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### Error Response (Example: Division by Zero)

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Body>
    <SOAP-ENV:Fault>
      <faultcode>DIVIDE_BY_ZERO</faultcode>
      <faultstring>Cannot divide by zero</faultstring>
    </SOAP-ENV:Fault>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

---

## Architecture

### Node.js Implementation
- **Framework**: Express.js with `soap` library
- **Port**: 3000
- **Endpoint**: POST `/soap`
- **WSDL**: `/calculator.wsdl`

### Spring Boot Implementation
- **Framework**: Spring Boot 3.2.0 with Spring Web Services 4.0.8
- **Port**: 8080
- **Endpoint**: POST `/ws/calculator`
- **WSDL**: `/ws/calculator.wsdl`
- **Build**: Maven with `./mvnw` wrapper
- **Java Version**: 17+

---

## Testing Checklist

- [ ] Add operation returns correct sum
- [ ] Subtract operation returns correct difference
- [ ] Multiply operation returns correct product
- [ ] Divide operation returns correct quotient
- [ ] Divide by zero returns SOAP fault
- [ ] Modulo operation returns correct remainder
- [ ] Power operation returns correct exponentiation
- [ ] CelsiusToFahrenheit returns 32 for 0°C input
- [ ] FahrenheitToCelsius returns 0 for 32°F input
- [ ] CelsiusToKelvin returns 273.15 for 0°C input

---

## Project Structure

```
tp-soap/
├── server.js                 # Node.js SOAP server
├── client.js                 # Node.js SOAP client
├── calculator.wsdl           # WSDL definition (Node.js)
├── package.json              # Node.js dependencies
└── calculator-soap-spring/   # Spring Boot project
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/com/example/calculator/
    │   │   │   ├── CalculatorApplication.java
    │   │   │   ├── config/WebServiceConfig.java
    │   │   │   ├── endpoint/CalculatorEndpoint.java
    │   │   │   ├── service/CalculatorService.java
    │   │   │   ├── model/
    │   │   │   │   ├── CalculatorRequest.java
    │   │   │   │   └── CalculatorResponse.java
    │   │   │   └── exception/
    │   │   │       ├── CalculatorException.java
    │   │   │       └── SoapExceptionHandler.java
    │   │   └── resources/
    │   │       ├── wsdl/calculator.wsdl
    │   │       └── application.properties
    │   └── test/
    │       └── java/com/example/calculator/
    │           └── service/CalculatorServiceTest.java
    └── README.md
```

---

## License

Both implementations are provided as-is for educational purposes.
