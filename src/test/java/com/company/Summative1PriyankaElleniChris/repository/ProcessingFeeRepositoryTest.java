package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.ProcessingFee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessingFeeRepositoryTest {

    @Autowired
    private ProcessingFeeRepository processingFeeRepository;

    @Before
    public void setUp() throws Exception {
        processingFeeRepository.deleteAll();
    }

    @Test
    public void shouldCreateReadDeleteProcessingFee() {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setFee(new BigDecimal("1.49"));
        processingFee.setProductType("Game");

        processingFeeRepository.save(processingFee);
        Optional<ProcessingFee> processingFee1= processingFeeRepository.findById(processingFee.getProductType());
        assertEquals(processingFee,processingFee1.get());

        processingFeeRepository.deleteById(processingFee.getProductType());
        processingFee1= processingFeeRepository.findById(processingFee.getProductType());
        assertFalse(processingFee1.isPresent());
    }

    @Test
    public void shouldUpdateProcessingFee(){
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setFee(new BigDecimal("1.49"));
        processingFee.setProductType("Game");
        ProcessingFee processingFee1 =processingFeeRepository.save(processingFee);
        processingFee.setFee(new BigDecimal("2.50"));
        ProcessingFee processingFee2= processingFeeRepository.save(processingFee);

        assertNotEquals(processingFee1,processingFee2);
    }

    @Test
    public void shouldGetAllProcessingFees(){
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setFee(new BigDecimal("1.49"));
        processingFee.setProductType("Game");
        processingFeeRepository.save(processingFee);

        ProcessingFee processingFee2 = new ProcessingFee();
        processingFee2.setFee(new BigDecimal("1.98"));
        processingFee2.setProductType("T-Shirt");
        processingFeeRepository.save(processingFee2);

        List<ProcessingFee> processingFeeList = processingFeeRepository.findAll();
        assertEquals(2,processingFeeList.size());
    }

}