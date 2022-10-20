package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    private InvoiceRepository invoiceRepository;

    private Invoice invoice;

    @Before
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();

        invoice = new Invoice();
        invoice.setName("Customer 1");
        invoice.setStreet("100 Main Street");
        invoice.setCity("Clovis");
        invoice.setState("CA");
        invoice.setZipcode("93612");
        invoice.setItemType("Game");
        invoice.setItemId(269);
        invoice.setQuantity(12);
        invoice.setUnitPrice(new BigDecimal("12.99"));
        invoice.setSubtotal(new BigDecimal("155.88"));
        invoice.setTax(new BigDecimal("9.35"));
        invoice.setProcessingFee(new BigDecimal("16.98"));
        invoice.setTotal(new BigDecimal ("182.21"));
    }

    // Test for create and Read by id
    @Test
    public void shouldCreateReadInvoice() {
        Invoice invoiceCreated = invoiceRepository.save(invoice);
        int id = invoiceCreated.getInvoiceId();
        assertEquals(true, invoiceRepository.existsById(id));

        invoice.setInvoiceId(id);
        assertEquals(Optional.of(invoice), invoiceRepository.findById(id));
    }

    // Test for Read All
    @Test
    public void shouldReadAllInvoices() {
        invoiceRepository.save(invoice);

        List<Invoice> invoices = invoiceRepository.findAll();

        assertEquals(1, invoices.size());
    }

    @Test
    public void shouldFindByCustomerName() {
        Invoice outputInvoice = invoiceRepository.save(invoice);

        List<Invoice> invoiceList = invoiceRepository.findByName("Customer 1");

        assertEquals(1, invoiceList.size());
    }
}