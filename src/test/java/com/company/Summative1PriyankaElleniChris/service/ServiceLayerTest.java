package com.company.Summative1PriyankaElleniChris.service;

import com.company.Summative1PriyankaElleniChris.model.*;
import com.company.Summative1PriyankaElleniChris.repository.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

public class ServiceLayerTest {

    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private InvoiceRepository invoiceRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private SalesTaxRateRepository salesTaxRateRepository;
    private T_shirtRepository t_shirtRepository;

    private ServiceLayer serviceLayer;


    @Before
    public void setUp() throws Exception {
        setUpConsoleRepositoryMock();
        setUpGameRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpProcessingFeeRepositoryMock();
        setUpSalesTaxRateRepositoryMock();
        setUpT_shirtRepositoryMock();

        serviceLayer =new ServiceLayer(consoleRepository,gameRepository,invoiceRepository,processingFeeRepository,salesTaxRateRepository,t_shirtRepository);
    }

    private void setUpT_shirtRepositoryMock() {
        t_shirtRepository =mock(T_shirtRepository.class);
        T_shirt t_shirt = new T_shirt();
        t_shirt.setId(1);
        t_shirt.setColor("White");
        t_shirt.setSize("Medium");
        t_shirt.setDescription("Made with cotton");
        t_shirt.setPrice(new BigDecimal("4.50"));
        t_shirt.setQuantity(6);


        doReturn(Optional.of(t_shirt)).when(t_shirtRepository).findById(1);
    }

    private void setUpSalesTaxRateRepositoryMock() {
        salesTaxRateRepository = mock(SalesTaxRateRepository.class);
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("CA");
        salesTaxRate.setRate(0.06f);

        SalesTaxRate salesTaxRate2 = new SalesTaxRate();
        salesTaxRate2.setState("NY");
        salesTaxRate2.setRate(0.06f);

        List salesTaxList = new ArrayList<>();
        salesTaxList.add(salesTaxRate);
        salesTaxList.add(salesTaxRate2);

        doReturn(Optional.of(salesTaxRate)).when(salesTaxRateRepository).findById("CA");

    }

    private void setUpProcessingFeeRepositoryMock() {
        processingFeeRepository = mock(ProcessingFeeRepository.class);
        ProcessingFee processingFee =new ProcessingFee();
        processingFee.setProductType("Game");
        processingFee.setFee(new BigDecimal("1.49"));

        doReturn(Optional.of(processingFee)).when(processingFeeRepository).findById("Game");

    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock (InvoiceRepository.class);
        Invoice invoice = new Invoice();
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

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(1);
        invoice2.setName("Customer 1");
        invoice2.setStreet("100 Main Street");
        invoice2.setCity("Clovis");
        invoice2.setState("CA");
        invoice2.setZipcode("93612");
        invoice2.setItemType("Game");
        invoice2.setItemId(269);
        invoice2.setQuantity(12);
        invoice2.setUnitPrice(new BigDecimal("12.99"));
        invoice2.setSubtotal(new BigDecimal("155.88"));
        invoice2.setTax(new BigDecimal("9.35"));
        invoice2.setProcessingFee(new BigDecimal("16.98"));
        invoice2.setTotal(new BigDecimal ("182.21"));

        doReturn(invoice2).when(invoiceRepository).save(invoice);


    }

    private void setUpGameRepositoryMock() {
        gameRepository =mock(GameRepository.class);
        Game game = new Game();
        game.setGameId(269);
        game.setDescription("its a Fun Game");
        game.setTitle("Title of the Game");
        game.setPrice(new BigDecimal("12.99"));
        game.setStudio("Sony");
        game.setEsrbRating("E");
        game.setQuantity(122);


        doReturn(Optional.of(game)).when(gameRepository).findById(269);
    }

    private void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);
        Console console = new Console();
        console.setId(1);
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);

        doReturn(Optional.of(console)).when(consoleRepository).findById(1);


    }

    @Test
    public void shouldSaveInvoice() {
        //Arrange
        Invoice inputInvoice = new Invoice();
        inputInvoice.setName("Customer 1");
        inputInvoice.setStreet("100 Main Street");
        inputInvoice.setCity("Clovis");
        inputInvoice.setState("CA");
        inputInvoice.setZipcode("93612");
        inputInvoice.setItemType("Game");
        inputInvoice.setItemId(269);
        inputInvoice.setQuantity(12);

        Invoice outputInvoice = new Invoice();
        outputInvoice.setInvoiceId(1);
        outputInvoice.setName("Customer 1");
        outputInvoice.setStreet("100 Main Street");
        outputInvoice.setCity("Clovis");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("93612");
        outputInvoice.setItemType("Game");
        outputInvoice.setItemId(269);
        outputInvoice.setQuantity(12);
        outputInvoice.setUnitPrice(new BigDecimal("12.99"));
        outputInvoice.setSubtotal(new BigDecimal("155.88"));
        outputInvoice.setTax(new BigDecimal("9.35"));
        outputInvoice.setProcessingFee(new BigDecimal("16.98"));
        outputInvoice.setTotal(new BigDecimal ("182.21"));

        //Act

       Invoice actualInvoice = serviceLayer.saveInvoice(inputInvoice);

       //Assert

        assertEquals(outputInvoice, actualInvoice);
    }
}