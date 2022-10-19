package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.Console;
import com.company.Summative1PriyankaElleniChris.repository.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    @Test
    public void shouldReturn422StatusCodeWithInvalidRequestBodyPOSTRequest() throws Exception {
        Console console = new Console();
        console.setModel("Switch");
//        console.setManufacturer("Nintendo"); Object without a Manufacturer
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);

        String consoleJSON =mapper.writeValueAsString(console);

        mockMvc.perform(
                post("/consoles")
                        .content(consoleJSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void shouldReturn404StatusCodeIfConsoleNotFound() throws Exception{
        mockMvc.perform(get("/consoles/-1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    public void shouldReturn422StatusCodeWithInvalidRequestBodyPUTRequest() throws Exception {
        Console console = new Console();
        console.setId(1);
        console.setModel("Switch");
//        console.setManufacturer("Nintendo"); Object without a Manufacturer
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);

        String consoleJSON =mapper.writeValueAsString(console);

        mockMvc.perform(
                        put("/consoles")
                                .content(consoleJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
     public void shouldReturnNewConsoleOnPostRequest() throws Exception{
        Console inputConsole = new Console();
        inputConsole.setModel("Switch");
        inputConsole.setManufacturer("Nintendo");
        inputConsole.setMemoryAmount("256GB");
        inputConsole.setProcessor("NVIDIA Tegra");
        inputConsole.setPrice( new BigDecimal("250.99"));
        inputConsole.setQuantity(12);

        String inputConsoleJSON =mapper.writeValueAsString(inputConsole);

        Console outputConsole= consoleRepository.save(inputConsole);

        String outputConsoleJSON = mapper.writeValueAsString(outputConsole);

        mockMvc.perform(
                post("/consoles")
                        .content(inputConsoleJSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())                     // ASSERT that we got back 200 OK.
                .andExpect(content().json(outputConsoleJSON));         // ASSERT that what we're expecting is what we got back.
    }
    @Test
    public void shouldReturnConsoleById() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setModel("Switch");
        inputConsole.setManufacturer("Nintendo");
        inputConsole.setMemoryAmount("256GB");
        inputConsole.setProcessor("NVIDIA Tegra");
        inputConsole.setPrice( new BigDecimal("250.99"));
        inputConsole.setQuantity(12);

        Console outputConsole= consoleRepository.save(inputConsole);

        String outputConsoleJSON = mapper.writeValueAsString(outputConsole);
        mockMvc.perform(get("/consoles/1"))
                .andDo(print())
                .andExpect(status().isOk())                     // ASSERT that we got back 200 OK.
                .andExpect(content().json(outputConsoleJSON));         // ASSERT that what we're expecting is what we got back.
    }
    @Test
    public void shouldReturnAllConsoles() throws Exception {

        // ARRANGE
        Console inputConsole = new Console();
        inputConsole.setModel("Switch");
        inputConsole.setManufacturer("Nintendo");
        inputConsole.setMemoryAmount("256GB");
        inputConsole.setProcessor("NVIDIA Tegra");
        inputConsole.setPrice( new BigDecimal("250.99"));
        inputConsole.setQuantity(12);

        consoleRepository.save(inputConsole);
        //ACT
        mockMvc.perform(get("/consoles"))       // Perform the GET request.
                .andDo(print())                          // Print results to console.
                .andExpect(status().isOk())              // ASSERT (status code is 200)
                .andExpect(jsonPath("$[0]").isNotEmpty());// ASSERT that the JSON array is present and not empty
    }

}