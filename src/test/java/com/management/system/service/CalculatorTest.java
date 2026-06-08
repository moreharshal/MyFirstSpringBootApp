package com.management.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Calculator service.
 * Tests addition functionality with various input scenarios (RAP-838/RAP-839).
 * 
 * @author Symphony Agent
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorTest {

    @Autowired
    private Calculator calculator;

    // Integer addition tests
    
    @Test
    public void testAddPositiveIntegers() {
        int result = calculator.add(5, 3);
        assertEquals(8, result);
    }

    @Test
    public void testAddNegativeIntegers() {
        int result = calculator.add(-5, -3);
        assertEquals(-8, result);
    }

    @Test
    public void testAddMixedSignIntegers() {
        int result = calculator.add(10, -3);
        assertEquals(7, result);
    }

    @Test
    public void testAddWithZero() {
        int result = calculator.add(0, 5);
        assertEquals(5, result);
    }

    @Test
    public void testAddZeroToZero() {
        int result = calculator.add(0, 0);
        assertEquals(0, result);
    }

    @Test
    public void testAddLargeIntegers() {
        int result = calculator.add(1000000, 2000000);
        assertEquals(3000000, result);
    }

    @Test
    public void testAddIntegerBoundary() {
        int result = calculator.add(Integer.MAX_VALUE - 1, 1);
        assertEquals(Integer.MAX_VALUE, result);
    }

    // Double addition tests
    
    @Test
    public void testAddPositiveDoubles() {
        double result = calculator.add(5.5, 3.2);
        assertEquals(8.7, result, 0.0001);
    }

    @Test
    public void testAddNegativeDoubles() {
        double result = calculator.add(-5.5, -3.2);
        assertEquals(-8.7, result, 0.0001);
    }

    @Test
    public void testAddMixedSignDoubles() {
        double result = calculator.add(10.5, -3.2);
        assertEquals(7.3, result, 0.0001);
    }

    @Test
    public void testAddDoubleWithZero() {
        double result = calculator.add(0.0, 5.5);
        assertEquals(5.5, result, 0.0001);
    }

    @Test
    public void testAddDecimalPrecision() {
        double result = calculator.add(0.1, 0.2);
        assertEquals(0.3, result, 0.0001);
    }

    @Test
    public void testAddVerySmallDoubles() {
        double result = calculator.add(0.0001, 0.0002);
        assertEquals(0.0003, result, 0.00001);
    }

    @Test
    public void testAddLargeDoubles() {
        double result = calculator.add(1000000.5, 2000000.3);
        assertEquals(3000000.8, result, 0.0001);
    }

    // Long addition tests
    
    @Test
    public void testAddPositiveLongs() {
        long result = calculator.add(5000000000L, 3000000000L);
        assertEquals(8000000000L, result);
    }

    @Test
    public void testAddNegativeLongs() {
        long result = calculator.add(-5000000000L, -3000000000L);
        assertEquals(-8000000000L, result);
    }

    @Test
    public void testAddMixedSignLongs() {
        long result = calculator.add(10000000000L, -3000000000L);
        assertEquals(7000000000L, result);
    }

    @Test
    public void testAddLongWithZero() {
        long result = calculator.add(0L, 5000000000L);
        assertEquals(5000000000L, result);
    }

    @Test
    public void testAddVeryLargeLongs() {
        long result = calculator.add(Long.MAX_VALUE - 1000, 1000);
        assertEquals(Long.MAX_VALUE, result);
    }
}
