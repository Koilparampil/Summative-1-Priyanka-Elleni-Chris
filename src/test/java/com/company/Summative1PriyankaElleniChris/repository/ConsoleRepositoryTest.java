package com.company.Summative1PriyankaElleniChris.repository;

import com.company.Summative1PriyankaElleniChris.model.Console;
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

    @Test
    public void getAllConsoles(){
        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);

        console= consoleRepository.save(console);

        console = new Console();
        console.setModel("DSiXL");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("64GB");
        console.setProcessor("ARM7");
        console.setPrice( new BigDecimal("169.99"));
        console.setQuantity(10);

        console= consoleRepository.save(console);

        List<Console> cList = consoleRepository.findAll();
        assertEquals(cList.size(),2);
    }
    @Test
    public void getConsolesByManufacturer(){
        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("256GB");
        console.setProcessor("NVIDIA Tegra");
        console.setPrice( new BigDecimal("250.99"));
        console.setQuantity(12);

        console= consoleRepository.save(console);

        Console console1 = new Console();
        console1.setModel("PSP");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("64GB");
        console1.setProcessor("MIPS32 ");
        console1.setPrice( new BigDecimal("279.99"));
        console1.setQuantity(11);

        console1= consoleRepository.save(console1);


        List<Console> consolesByMan = consoleRepository.findByManufacturer("Sony");
        assertEquals(1,consolesByMan.size());
    }

}