package com.company.Summative1PriyankaElleniChris.repository;
import com.company.Summative1PriyankaElleniChris.model.T_shirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class T_shirtRepositoryTest {
    @Autowired
    T_shirtRepository t_shirtRepository;
    private T_shirt t_shirt;

    @Before
    public void setUp() throws Exception {

        t_shirtRepository.deleteAll();

    }

    @Test
    public void getDeletedT_shirt(){
        T_shirt t_shirt = new T_shirt();
        t_shirt.setColor("White");
        t_shirt.setSize("Medium");
        t_shirt.setDescription("Made with cotton");
        t_shirt.setPrice(new BigDecimal("4.50"));
        t_shirt.setQuantity(6);
        t_shirtRepository.save(t_shirt);

        Optional<T_shirt> t_shirt1 = t_shirtRepository.findById(t_shirt.getId());

        assertEquals(t_shirt1.get(), t_shirt);
        t_shirtRepository.deleteById(t_shirt.getId());
        t_shirt1 = t_shirtRepository.findById(t_shirt.getId());

        assertFalse(t_shirt1.isPresent());

    }

    @Test
    public void getAllT_shirt(){
        T_shirt t_shirt1 = new T_shirt();
       t_shirt1.setPrice(new BigDecimal("5.75"));
       t_shirt1.setSize("Large");
       t_shirt1.setQuantity(6);
       t_shirt1.setColor("Blue");
       t_shirt1.setDescription("Made in Ethiopia");
       t_shirtRepository.save(t_shirt1);

        T_shirt t_shirt2 = new T_shirt();
        t_shirt2.setPrice(new BigDecimal("5.07"));
        t_shirt2.setSize("Medium");
        t_shirt2.setQuantity(4);
        t_shirt2.setColor("Yellow");
        t_shirt2.setDescription("Made in Ethiopia");
        t_shirtRepository.save(t_shirt2);

        T_shirt t_shirt3 = new T_shirt();
        t_shirt3.setPrice(new BigDecimal("7.60"));
        t_shirt3.setSize("Medium");
        t_shirt3.setQuantity(2);
        t_shirt3.setColor("Yellow");
        t_shirt3.setDescription("Made in Ethiopia");
        t_shirtRepository.save(t_shirt3);

        List<T_shirt>t_shirtsList = t_shirtRepository.findAll();
        assertEquals(3, t_shirtsList.size());
    }
    @Test
    public void getT_shirtByColorAndSize() {
        T_shirt t_shirt1 = new T_shirt();
        t_shirt1.setPrice(new BigDecimal("5.75"));
        t_shirt1.setSize("Large");
        t_shirt1.setQuantity(6);
        t_shirt1.setColor("Blue");
        t_shirt1.setDescription("Made in Ethiopia");
        t_shirtRepository.save(t_shirt1);

        T_shirt t_shirt2 = new T_shirt();
        t_shirt2.setPrice(new BigDecimal("5.07"));
        t_shirt2.setSize("Medium");
        t_shirt2.setQuantity(4);
        t_shirt2.setColor("Yellow");
        t_shirt2.setDescription("Made in Ethiopia");
        t_shirtRepository.save(t_shirt2);

        T_shirt t_shirt3 = new T_shirt();
        t_shirt3.setPrice(new BigDecimal("7.60"));
        t_shirt3.setSize("Medium");
        t_shirt3.setQuantity(2);
        t_shirt3.setColor("Yellow");
        t_shirt3.setDescription("Made in Ethiopia");
        t_shirtRepository.save(t_shirt3);

        List<T_shirt>blueT_shirtList = t_shirtRepository.findT_shirtByColor("Blue");
        List<T_shirt>mediumSizeT_shirt = t_shirtRepository.findT_shirtBySize("Medium");
        assertEquals(1, blueT_shirtList.size());
        assertEquals(2, mediumSizeT_shirt.size());
    }
    @Test
    public void updateT_shirt() {
        T_shirt tShirt = new T_shirt();
        tShirt.setPrice(new BigDecimal("7.60"));
        tShirt.setSize("Medium");
        tShirt.setQuantity(2);
        tShirt.setColor("Yellow");
        tShirt.setDescription("Made in Ethiopia");
       tShirt =  t_shirtRepository.save(tShirt);

        tShirt.setPrice(new BigDecimal("8.00"));
        tShirt.setSize("Medium");
        tShirt.setQuantity(2);
        tShirt.setColor("Yellow");
        tShirt.setDescription("Made in Ethiopia");
        t_shirtRepository.save(tShirt);

        Optional<T_shirt>tShirt1 = t_shirtRepository.findById(tShirt.getId());
        assertEquals(tShirt1.get(), tShirt);
    }

    }