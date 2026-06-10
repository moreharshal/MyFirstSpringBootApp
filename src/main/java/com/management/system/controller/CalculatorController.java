package com.management.system.controller;

import com.management.system.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for calculator operations.
 * Provides HTTP endpoints for addition functionality (RAP-838/RAP-839).
 * 
 * @author Symphony Agent
 * @version 1.0
 */
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final Calculator calculator;

    @Autowired
    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Adds two numbers via GET request.
     * 
     * @param a first number (required)
     * @param b second number (required)
     * @return JSON response with the sum
     * 
     * Example: GET /api/calculator/add?a=5&b=3
     * Response: {"operand1": 5.0, "operand2": 3.0, "result": 8.0, "operation": "addition"}
     */
    @GetMapping("/add")
    public ResponseEntity<AdditionResponse> add(
            @RequestParam(name = "a") double a,
            @RequestParam(name = "b") double b) {
        
        double result = calculator.add(a, b);
        AdditionResponse response = new AdditionResponse(a, b, result, "addition");
        return ResponseEntity.ok(response);
    }

    /**
     * Adds two numbers via POST request with JSON body.
     * 
     * @param request the addition request containing two operands
     * @return JSON response with the sum
     * 
     * Example: POST /api/calculator/add
     * Body: {"operand1": 10, "operand2": 20}
     * Response: {"operand1": 10.0, "operand2": 20.0, "result": 30.0, "operation": "addition"}
     */
    @PostMapping("/add")
    public ResponseEntity<AdditionResponse> addPost(@RequestBody AdditionRequest request) {
        double result = calculator.add(request.getOperand1(), request.getOperand2());
        AdditionResponse response = new AdditionResponse(
                request.getOperand1(),
                request.getOperand2(),
                result,
                "addition"
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint for calculator service.
     * 
     * @return status message
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Calculator service is running");
    }

    /**
     * Request DTO for addition operation.
     */
    public static class AdditionRequest {
        private double operand1;
        private double operand2;

        public AdditionRequest() {}

        public AdditionRequest(double operand1, double operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
        }

        public double getOperand1() {
            return operand1;
        }

        public void setOperand1(double operand1) {
            this.operand1 = operand1;
        }

        public double getOperand2() {
            return operand2;
        }

        public void setOperand2(double operand2) {
            this.operand2 = operand2;
        }
    }

    /**
     * Response DTO for addition operation.
     */
    public static class AdditionResponse {
        private double operand1;
        private double operand2;
        private double result;
        private String operation;

        public AdditionResponse() {}

        public AdditionResponse(double operand1, double operand2, double result, String operation) {
            this.operand1 = operand1;
            this.operand2 = operand2;
            this.result = result;
            this.operation = operation;
        }

        public double getOperand1() {
            return operand1;
        }

        public void setOperand1(double operand1) {
            this.operand1 = operand1;
        }

        public double getOperand2() {
            return operand2;
        }

        public void setOperand2(double operand2) {
            this.operand2 = operand2;
        }

        public double getResult() {
            return result;
        }

        public void setResult(double result) {
            this.result = result;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }
    }
}
