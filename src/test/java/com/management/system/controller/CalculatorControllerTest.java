package com.management.system.controller;

import com.management.system.service.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for CalculatorController REST endpoints.
 * Tests HTTP endpoints for addition functionality (RAP-838/RAP-839).
 * 
 * @author Symphony Agent
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculator;

    @Test
    public void testAddEndpointWithQueryParams() throws Exception {
        when(calculator.add(5.0, 3.0)).thenReturn(8.0);

        mockMvc.perform(get("/api/calculator/add")
                .param("a", "5.0")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.operand1").value(5.0))
                .andExpect(jsonPath("$.operand2").value(3.0))
                .andExpect(jsonPath("$.result").value(8.0))
                .andExpect(jsonPath("$.operation").value("addition"));
    }

    @Test
    public void testAddEndpointWithNegativeNumbers() throws Exception {
        when(calculator.add(-5.0, -3.0)).thenReturn(-8.0);

        mockMvc.perform(get("/api/calculator/add")
                .param("a", "-5.0")
                .param("b", "-3.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(-8.0));
    }

    @Test
    public void testAddEndpointWithDecimalNumbers() throws Exception {
        when(calculator.add(5.5, 3.2)).thenReturn(8.7);

        mockMvc.perform(get("/api/calculator/add")
                .param("a", "5.5")
                .param("b", "3.2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.7));
    }

    @Test
    public void testAddEndpointWithZero() throws Exception {
        when(calculator.add(0.0, 5.0)).thenReturn(5.0);

        mockMvc.perform(get("/api/calculator/add")
                .param("a", "0.0")
                .param("b", "5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    public void testAddEndpointMissingParameter() throws Exception {
        mockMvc.perform(get("/api/calculator/add")
                .param("a", "5.0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddPostEndpointWithJsonBody() throws Exception {
        when(calculator.add(10.0, 20.0)).thenReturn(30.0);

        String jsonRequest = "{\"operand1\": 10.0, \"operand2\": 20.0}";

        mockMvc.perform(post("/api/calculator/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.operand1").value(10.0))
                .andExpect(jsonPath("$.operand2").value(20.0))
                .andExpect(jsonPath("$.result").value(30.0))
                .andExpect(jsonPath("$.operation").value("addition"));
    }

    @Test
    public void testAddPostEndpointWithNegativeNumbers() throws Exception {
        when(calculator.add(-15.5, -10.3)).thenReturn(-25.8);

        String jsonRequest = "{\"operand1\": -15.5, \"operand2\": -10.3}";

        mockMvc.perform(post("/api/calculator/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(-25.8));
    }

    @Test
    public void testAddPostEndpointWithLargeNumbers() throws Exception {
        when(calculator.add(1000000.0, 2000000.0)).thenReturn(3000000.0);

        String jsonRequest = "{\"operand1\": 1000000.0, \"operand2\": 2000000.0}";

        mockMvc.perform(post("/api/calculator/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(3000000.0));
    }

    @Test
    public void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculator service is running"));
    }
}
