package com.ascending.training.repository;

import com.ascending.training.model.Cat;
import com.ascending.training.model.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HibernateCatTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Cat a;
    private CatDaoImpl catDaoImpl = new CatDaoImpl();
    private PetDaoImpl petDaoImpl = new PetDaoImpl();


    @Before
    public void init(){
        long owner_id = 12;

        a = new Cat();
        Pet pet = petDaoImpl.getPetsByName("Yveltal").get(0);
//        a.setId(30);
        a.setCalici('Y');
//        a.setOwnerid(owner_id);
        a.setDeworm('N');
        a.setPan('Y');
        a.setRhi('Y');
        a.setRabies('Y');
        a.setSpayNeuter('N');
        a.setName("Yveltal");


        catDaoImpl.saveCat(a,owner_id);
    }

   @After
   public void cleanUp(){
       catDaoImpl.delete(a.getId());
       catDaoImpl = null;
       assertNull(catDaoImpl);
   }



    @Test
    public void saveTest() {
        String testName = "Kite";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Cat rio = catDaoImpl.getCatsByName("YYzz").get(0);
        String newName = "Rio";
        rio.setName(newName);
        catDaoImpl.update(rio);

        assertEquals(rio.getName(), newName);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = catDaoImpl.delete(a.getId());

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getCatsTest(){
        List<Cat> all = catDaoImpl.getCats();
        int expectedOfNumber = 4;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getCatByNameTest(){
        String testName = "YYzz";
        Cat test = catDaoImpl.getCatsByName("YYzz").get(0);

        assertEquals(testName, test.getName());
    }
}
