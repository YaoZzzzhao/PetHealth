package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Cat;
import com.ascending.training.model.Pet;
import com.ascending.training.repository.CatDaoImpl;
import com.ascending.training.service.PetService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class CatServiceTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Cat a;

    @Autowired
    private CatDaoImpl catService;

    @Autowired
    private PetService petService;


    @Before
    public void init(){
//        long owner_id = 12;

        a = new Cat();
        Pet pet = petService.getPetsByName("Shy").get(0);
//        a.setId(30);
        a.setCalici('Y');
//        a.setOwnerid(owner_id);
        a.setDeworm('N');
        a.setPan('Y');
        a.setRhi('Y');
        a.setRabies('Y');
        a.setSpayNeuter('N');
        a.setName("XDD2.0");


        catService.saveCat(a,pet.getId());
    }

    @After
    public void cleanUp(){
        catService.delete(a.getId());
        catService = null;
        assertNull(catService);
    }



    @Test
    public void saveTest() {
        String testName = "XDD2.0";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Cat kite = catService.getCatsByName(a.getName()).get(0);
        String newName = "Jane";
        kite.setName(newName);
        catService.update(kite);

        assertEquals(kite.getName(), newName);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = catService.delete(a.getId());

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getCatsTest(){
        List<Cat> all = catService.getCats();
        int expectedOfNumber = 21;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getCatByNameTest(){
        String testName = "Jane";
        Cat test = catService.getCatsByName("Jane").get(0);

        assertEquals(testName, test.getName());
    }


}
