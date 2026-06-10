# Calculator Addition Feature - RAP-838/RAP-839

## Overview
This implementation adds calculator functionality to the Spring Boot application for workflow verification testing as per Jira stories RAP-838 and RAP-839.

## Implementation Details

### Components Created

1. **Calculator Service** (`com.management.system.service.Calculator`)
   - Spring `@Service` component
   - Provides addition operations for `int`, `double`, and `long` types
   - Thread-safe and stateless design

2. **Calculator REST Controller** (`com.management.system.controller.CalculatorController`)
   - RESTful API endpoints at `/api/calculator`
   - Supports both GET and POST methods
   - JSON request/response format
   - Comprehensive error handling

3. **Unit Tests** (`com.management.system.service.CalculatorTest`)
   - 19 test cases covering all addition methods
   - Tests positive, negative, zero, and boundary conditions
   - Validates integer, double, and long operations

4. **Integration Tests** (`com.management.system.controller.CalculatorControllerTest`)
   - 9 test cases for REST endpoints
   - MockMvc-based HTTP testing
   - Validates request/response JSON structure
   - Tests error scenarios (missing parameters)

## API Endpoints

### 1. GET /api/calculator/add
Add two numbers using query parameters.

**Request:**
```
GET /api/calculator/add?a=5&b=3
```

**Response:**
```json
{
  "operand1": 5.0,
  "operand2": 3.0,
  "result": 8.0,
  "operation": "addition"
}
```

### 2. POST /api/calculator/add
Add two numbers using JSON body.

**Request:**
```
POST /api/calculator/add
Content-Type: application/json

{
  "operand1": 10.5,
  "operand2": 20.3
}
```

**Response:**
```json
{
  "operand1": 10.5,
  "operand2": 20.3,
  "result": 30.8,
  "operation": "addition"
}
```

### 3. GET /api/calculator/health
Health check endpoint.

**Response:**
```
Calculator service is running
```

## Building and Testing

### Build the Application
```bash
mvn clean package
```

### Run Unit Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=CalculatorTest
mvn test -Dtest=CalculatorControllerTest
```

### Start the Application
```bash
mvn spring-boot:run
```

Or run the JAR:
```bash
java -jar target/system-0.0.1-SNAPSHOT.jar
```

## Manual Testing

Once the application is running (default port 8080):

### Using curl

**Test GET endpoint:**
```bash
curl "http://localhost:8080/api/calculator/add?a=15&b=25"
```

**Test POST endpoint:**
```bash
curl -X POST http://localhost:8080/api/calculator/add \
  -H "Content-Type: application/json" \
  -d '{"operand1": 100, "operand2": 200}'
```

**Test health endpoint:**
```bash
curl http://localhost:8080/api/calculator/health
```

### Using Browser
Open in browser:
```
http://localhost:8080/api/calculator/add?a=10&b=20
```

## Test Coverage Summary

**Total Test Cases:** 28
- Calculator Service: 19 tests
- Controller Integration: 9 tests

**Test Scenarios:**
- ✅ Positive numbers (integers, doubles, longs)
- ✅ Negative numbers
- ✅ Mixed sign operations
- ✅ Zero handling
- ✅ Decimal precision
- ✅ Large numbers
- ✅ Boundary conditions (MAX_VALUE)
- ✅ HTTP GET with query parameters
- ✅ HTTP POST with JSON body
- ✅ Error handling (missing parameters)
- ✅ Response format validation

## Files Modified/Created

**New Files:**
- `src/main/java/com/management/system/service/Calculator.java`
- `src/main/java/com/management/system/controller/CalculatorController.java`
- `src/test/java/com/management/system/service/CalculatorTest.java`
- `src/test/java/com/management/system/controller/CalculatorControllerTest.java`
- `CALCULATOR_README.md`

**Total Lines of Code:** ~330 (production) + ~270 (tests) = ~600 lines

## Acceptance Criteria Met

✅ Create sample application for addition of two numbers (RAP-839)  
✅ Implement addition functionality with comprehensive test coverage  
✅ RESTful API design following Spring Boot best practices  
✅ Proper documentation and examples  
✅ Manual verification instructions provided  

## Next Steps

1. Run automated tests: `mvn test`
2. Start application: `mvn spring-boot:run`
3. Test endpoints manually using curl or browser
4. Review code quality and documentation
5. Ready for code review and merge

---
**Jira Stories:** RAP-838, RAP-839  
**Repository:** https://github.com/moreharshal/MyFirstSpringBootApp  
**Branch:** RAP-838  
**Status:** Implementation Complete
