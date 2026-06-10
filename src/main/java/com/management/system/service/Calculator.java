package com.management.system.service;

import org.springframework.stereotype.Service;

/**
 * Calculator service for performing arithmetic operations.
 * Implements addition functionality for workflow verification testing (RAP-838/RAP-839).
 * 
 * @author Symphony Agent
 * @version 1.0
 */
@Service
public class Calculator {

    /**
     * Adds two integer numbers.
     * 
     * @param a first integer operand
     * @param b second integer operand
     * @return the sum of a and b
     */
    public int add( a,  b) {
        return a + b;
    }

    /**
     * Adds two double numbers with decimal precision.
     * 
     * @param a first double operand
     * @param b second double operand
     * @return the sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Adds two long numbers for large value support.
     * 
     * @param a first long operand
     * @param b second long operand
     * @return the sum of a and b
     */
    public long add(long a, long b) {
        return a + b;
    }
}
