package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.SalesTaxRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesTaxRateRepositoryTest {

    @Autowired
    private SalesTaxRateRepository salesTaxRateRepository;

    @Before
    public void setUp() throws Exception {
        salesTaxRateRepository.deleteAll();
    }

    @Test
    public void shouldCreateReadDeleteSalesTaxRate() {
        SalesTaxRate taxRate = new SalesTaxRate();
        taxRate.setState("NY");
        taxRate.setRate(0.06f);

        salesTaxRateRepository.save(taxRate);
        Optional<SalesTaxRate> taxRate1= salesTaxRateRepository.findById(taxRate.getState());
        assertEquals(taxRate1.get(),taxRate);

        salesTaxRateRepository.deleteById(taxRate.getState());
        taxRate1 = salesTaxRateRepository.findById(taxRate.getState());
        assertFalse(taxRate1.isPresent());
    }

    @Test
    public void shouldUpdateSalesTaxRate(){
        SalesTaxRate taxRate = new SalesTaxRate();
        taxRate.setState("NY");
        taxRate.setRate(0.06f);
        SalesTaxRate taxRate1 = salesTaxRateRepository.save(taxRate);
        taxRate.setRate(0.09f);
        SalesTaxRate taxRate2 = salesTaxRateRepository.save(taxRate);

        assertNotEquals(taxRate1,taxRate2);
    }

    @Test
    public void shouldGetAllSalesTaxRates(){
        SalesTaxRate taxRate = new SalesTaxRate();
        taxRate.setState("NY");
        taxRate.setRate(0.06f);
        salesTaxRateRepository.save(taxRate);

        SalesTaxRate taxRate2 = new SalesTaxRate();
        taxRate2.setState("KY");
        taxRate2.setRate(0.04f);
        salesTaxRateRepository.save(taxRate2);

        List<SalesTaxRate> salesTaxRateList = salesTaxRateRepository.findAll();
        assertEquals(salesTaxRateList.size(),2);
    }

}