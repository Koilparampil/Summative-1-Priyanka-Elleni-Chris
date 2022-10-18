package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    @Test
    public void addGetDeleteConsole(){
        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);

        console= consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getId());

        assertEquals(console1.get(),console);

        consoleRepository.deleteById(console.getId());

        console1 =consoleRepository.findById(console.getId());

        assertFalse(console1.isPresent());

    }
    @Test
    public void updateConsole(){
        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);

        Console console1= consoleRepository.save(console);

        console.setModel("DSiXL");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("64GB");
        console.setProcessor("ARM7");
        console.setPrice( new BigDecimal("169.99"));
        console.setQuantity(10);

        consoleRepository.save(console);

        Optional<Console> console2 = consoleRepository.findById(console1.getId());
        assertFalse(console1==console2.get());
    }




}