package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.T_shirt;
import com.company.Summative1PriyankaElleniChris.repository.T_shirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(T_shirtController.class)
public class T_shirtControllerTest {

   @Autowired
    private MockMvc mockMvc;
   private ObjectMapper mapper = new ObjectMapper();

  @MockBean
  private T_shirtRepository t_shirtRepository;

  private  String inputTshirtJson;
  private  String outputTshirtJson;

   @Before
    public void setUp() throws  Exception{

   }
   @Test
    public void ShouldReturnAllTshirtsInCollection()throws Exception {

       mockMvc.perform(get("/Tshirt"))
               .andDo(print())
               .andExpect(status().isOk());

    }
    @Test
    public void ShouldReturnAnewTshirt() throws Exception {
        T_shirt inputTshirt = new T_shirt();
        inputTshirt.setSize("large");
        inputTshirt.setColor("Blue");
        inputTshirt.setDescription("Made in USA");
        inputTshirt.setQuantity(6);
        inputTshirt.setPrice(new BigDecimal("8.00"));


        String inputTshirtJson = mapper.writeValueAsString(inputTshirt);

        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setSize("large");
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.00"));

        String outputTshirtJson = mapper.writeValueAsString(outputTshirt);
        doReturn(outputTshirt).when(t_shirtRepository).save(inputTshirt);

        mockMvc.perform(post("/Tshirt")
                .content(inputTshirtJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                 .andExpect(status().isCreated())
                .andExpect(content().json(outputTshirtJson));
    }
    @Test
    public void ShouldReturnUpdatedTshirt() throws Exception {
        T_shirt inputTshirt = new T_shirt();
        inputTshirt.setId(1);
        inputTshirt.setSize("Medium");
        inputTshirt.setColor("Blue");
        inputTshirt.setDescription("Made in USA");
        inputTshirt.setQuantity(6);
        inputTshirt.setPrice(new BigDecimal("8.00"));

        String inputTshirtJson = mapper.writeValueAsString(inputTshirt);


        mockMvc.perform(put("/Tshirt")
                        .content(inputTshirtJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNoContent());
    }





    @Test
    public void ShouldReturnTshirtWithColor() throws Exception {
        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setSize("large");
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.00"));

        List<T_shirt>t_shirtList= new ArrayList<>();
        t_shirtList.add(outputTshirt);

        outputTshirtJson = mapper.writeValueAsString(t_shirtList);
        doReturn(t_shirtList).when(t_shirtRepository).findT_shirtByColor("Blue");

        mockMvc.perform(get("/Tshirt/color/{color}", "Blue"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTshirtJson));
    }

    @Test
    public void ShouldReturnTshirtById() throws Exception {
        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setSize("large");
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.00"));


       String  outputTshirtJson = mapper.writeValueAsString(outputTshirt);
        doReturn(Optional.of(outputTshirt)).when(t_shirtRepository).findById(1);

        mockMvc.perform(get("/Tshirt/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTshirtJson));
    }


    @Test
    public void ShouldReturnTshirtWithSize() throws Exception {
        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setSize("large");
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.00"));

        List<T_shirt>t_shirtList= new ArrayList<>();
        t_shirtList.add(outputTshirt);

        outputTshirtJson = mapper.writeValueAsString(t_shirtList);
        doReturn(t_shirtList).when(t_shirtRepository).findT_shirtBySize("large");

        mockMvc.perform(get("/Tshirt/size/{size}", "large"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTshirtJson));
    }
    @Test
    public void ShouldDeleteById() throws Exception {
        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setSize("large");
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.00"));


       String outputTshirtJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(delete("/Tshirt/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());

    }
    @Test
    public void ShouldReturn422WhenSizeFieldMissed() throws Exception {
        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.00"));


        String outputTshirtJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(post("/Tshirt")
                        .content(outputTshirtJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void ShouldReturn422WhenPriceBiggerThat2Fraction() throws Exception {
        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setSize("large");
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.0078"));


        String outputTshirtJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(post("/Tshirt")
                        .content(outputTshirtJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void ShouldReturn422WhenSizeBiggerThat20() throws Exception {
        T_shirt outputTshirt = new T_shirt();
        outputTshirt.setId(1);
        outputTshirt.setSize("largeLargelargeLARGELarge");
        outputTshirt.setColor("Blue");
        outputTshirt.setDescription("Made in USA");
        outputTshirt.setQuantity(6);
        outputTshirt.setPrice(new BigDecimal("8.0078"));


        String outputTshirtJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(post("/Tshirt")
                        .content(outputTshirtJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}



