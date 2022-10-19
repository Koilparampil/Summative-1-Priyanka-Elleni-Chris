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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    ConsoleRepository consoleRepository;

    private Console console;
    private String consoleJSON;

    private Console console1;
    private String console1JSON;

    private Console console2;

    private String console2JSON;

    private List<Console> allConsoles =new ArrayList<>();
    private String allConsolesJSON;
    @Before
    public void setUp() throws Exception {
        //Object with no ID
        console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);
        consoleJSON = mapper.writeValueAsString(console);


        //Same Object with ID
        console2 = new Console();
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setMemoryAmount("256GB");
        console2.setProcessor("NVIDIA Tegra");
        console2.setPrice( new BigDecimal("250.99"));
        console2.setQuantity(12);
        console2.setId(1);
        console2JSON = mapper.writeValueAsString(console2);

        allConsoles.add(console2);
        allConsolesJSON =mapper.writeValueAsString(allConsoles);

        //Unfinished Object
        console1 = new Console();
        console1.setModel("Switch2");
        console1.setMemoryAmount("512GB");
        console1.setProcessor("NVIDIA Tegra");
        console1.setPrice( new BigDecimal("300.99"));
        console1.setQuantity(2);
        console1JSON = mapper.writeValueAsString(console1);
    }

    @Test
    public void shouldReturn422StatusCodeWithInvalidRequestBodyPOSTRequest() throws Exception {
        mockMvc.perform(
                post("/consoles")
                        .content(console1JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void shouldReturn422StatusCodeIfConsoleNotFound() throws Exception{
        mockMvc.perform(get("/consoles/-1"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void shouldReturn422StatusCodeWithInvalidRequestBodyPUTRequest() throws Exception {
        mockMvc.perform(
                        put("/consoles")
                                .content(console1JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
     public void shouldReturnNewConsoleOnPostRequest() throws Exception{
        doReturn(console2).when(consoleRepository).save(console);

        mockMvc.perform(
                post("/consoles")
                        .content(consoleJSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())                     // ASSERT that we got back 200 OK.
                .andExpect(content().json(console2JSON));         // ASSERT that what we're expecting is what we got back.
    }
    @Test
    public void shouldReturnConsoleById() throws Exception {
        Optional<Console> optConsole = Optional.of(console2);
        doReturn(optConsole).when(consoleRepository).findById(1);
        mockMvc.perform(get("/consoles/1"))
                .andDo(print())
                .andExpect(status().isOk())                     // ASSERT that we got back 200 OK.
                .andExpect(content().json(console2JSON));         // ASSERT that what we're expecting is what we got back.
    }
    @Test
    public void shouldReturnAllConsoles() throws Exception {

        doReturn(allConsoles).when(consoleRepository).findAll();

        mockMvc.perform(get("/consoles"))       // Perform the GET request.
                .andDo(print())                          // Print results to console.
                .andExpect(status().isOk())              // ASSERT (status code is 200)
                .andExpect(jsonPath("$[0]").isNotEmpty());// ASSERT that the JSON array is present and not empty
    }

    @Test
    public void shouldUpdateConsoleAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                        put("/consoles")
                                .content(console2JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent()); // ASSERT that we got back 204 NO CONTENT.

    }
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/consoles/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void shouldReturn422StatusCodeIfConsoleNotFoundForDeletion() throws Exception{
        mockMvc.perform(get("/consoles/-1"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}