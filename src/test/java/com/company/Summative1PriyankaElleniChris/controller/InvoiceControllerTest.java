package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.Invoice;
import com.company.Summative1PriyankaElleniChris.repository.InvoiceRepository;
import com.company.Summative1PriyankaElleniChris.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
    private InvoiceRepository invoiceRepository;

   @MockBean
   private ServiceLayer serviceLayer;
   private ObjectMapper mapper = new ObjectMapper();
   private Invoice inputInvoice1;
   private Invoice inputInvoice2;
   private Invoice inputInvoice3;
   private Invoice inputInvoice4;
   private List<Invoice>inputInvoiceList = new ArrayList<>();

   private String inputInvoice1Json;
    private String inputInvoice2Json;
    private String inputInvoice3Json;
   private String inputInvoice4Json;
   private Invoice outputInvoice1;
   private  Invoice outputInvoice2;
    private Invoice outputInvoice3;
    private Invoice outputInvoice4;
   private List<Invoice>outputInvoiceList = new ArrayList<>();

   private  String  outputInvoice1Json;
   private  String  outputInvoice2Json;
   private  String  outputInvoice4Json;
   private String outputListJson;

   @Before
    public void setup() throws Exception {

       inputInvoice1 = new Invoice("Customer 1", "100 Mani Street", "Clovis", "CA", "93612", "Game", 269,  12);
       inputInvoice2 = new Invoice("Customer 2", "101 Mani Street", "Clovis", "NC", "93612", "Game", 220,  10);
       inputInvoice3 = new Invoice("Customer 3", "102 Mani Street", "Clovis", "NY", "93612", "Game", 165,  9);
       inputInvoice4 = new Invoice("Customer 3", "102 Mani Street", "Clovis", "NY", "93612", "Game", 165,  9);

       inputInvoiceList.add(inputInvoice1);
       inputInvoiceList.add(inputInvoice2);
       inputInvoiceList.add(inputInvoice3);

       inputInvoice1Json = mapper.writeValueAsString(inputInvoice1);
       inputInvoice2Json = mapper.writeValueAsString(inputInvoice2);
       inputInvoice3Json = mapper.writeValueAsString(inputInvoice3);

       outputInvoice1 = new Invoice(1, "Customer 1", "100 Mani Street", "Clovis", "CA", "93612", "Game", 269, (new BigDecimal("12.99")), 12, (new BigDecimal("155.88")), (new BigDecimal("9.35")), (new BigDecimal("16.98")), (new BigDecimal("182.21")));
       outputInvoice2 = new Invoice(2, "Customer 2", "101 Mani Street", "Clovis", "NC", "93612", "Game", 220, (new BigDecimal("12.99")), 10, (new BigDecimal("155.88")), (new BigDecimal("9.35")), (new BigDecimal("16.98")), (new BigDecimal("182.21")));
       outputInvoice3 = new Invoice(3, "Customer 3", "102 Mani Street", "Clovis", "NY", "93612", "Game", 165, (new BigDecimal("12.99")), 9, (new BigDecimal("155.88")), (new BigDecimal("9.35")), (new BigDecimal("16.98")), (new BigDecimal("182.21")));
       outputInvoice4 = new Invoice(4, "Customer 3", "102 Mani Street", "Clovis", "NY", "93612", "Game", 165, (new BigDecimal("12.99")), 9, (new BigDecimal("155.88")), (new BigDecimal("9.35")), (new BigDecimal("16.98")), (new BigDecimal("182.21")));

       outputInvoiceList.add(outputInvoice1);
       outputInvoiceList.add(outputInvoice2);
       outputInvoiceList.add(outputInvoice3);
       outputInvoiceList.add(outputInvoice4);

       outputInvoice1Json = mapper.writeValueAsString(outputInvoice1);
       outputInvoice2Json = mapper.writeValueAsString(outputInvoice2);
       outputInvoice4Json = mapper.writeValueAsString(outputInvoice4);
       outputListJson = mapper.writeValueAsString(outputInvoiceList);
   }

   @Test
   public void shouldCreateInvoice() throws Exception {
       doReturn(outputInvoice4).when(serviceLayer).saveInvoice(inputInvoice4);
        inputInvoice4Json = mapper.writeValueAsString(inputInvoice4);

       mockMvc.perform(post("/invoices")
               .content(inputInvoice4Json )
               .contentType(MediaType.APPLICATION_JSON))

               .andDo(print())
               .andExpect(status().isCreated())
               .andExpect(content().json(outputInvoice4Json));

       }


   @Test
    public void shouldReturnAllInvoice() throws Exception {
       doReturn(outputInvoiceList).when(invoiceRepository).findAll();

       mockMvc.perform(get("/invoices"))
               .andExpect(status().isOk())
               .andExpect(content().json(outputListJson));
   }

   @Test
    public void shouldReturnInvoiceById() throws Exception {
       doReturn(Optional.of(outputInvoice1)).when(invoiceRepository).findById(1);

        mockMvc.perform(get("/invoices/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputInvoice1Json));

   }

   @Test
    public void shouldReturnInvoiceByName() throws Exception {
       List<Invoice>invoiceList = new ArrayList<>();
       invoiceList.add(outputInvoice1);
       invoiceList.add(outputInvoice3);

       String outputJson = mapper.writeValueAsString(invoiceList);

       doReturn(invoiceList).when(invoiceRepository).findByName("Customer1");

       mockMvc.perform(get("/invoices/CustomerName/Customer1"))
               .andExpect(status().isOk())
               .andExpect(content().json(outputJson));
   }











}