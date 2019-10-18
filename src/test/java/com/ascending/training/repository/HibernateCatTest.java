package com.ascending.training.repository;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Cat;
import com.ascending.training.model.Pet;
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
public class HibernateCatTest {

    @Autowired
    Logger logger;
//    = LoggerFactory.getLogger(this.getClass());
    private Cat a;
    @Autowired
    private CatDaoImpl catDaoImpl;
//    = new CatDaoImpl();
    @Autowired
    private PetDaoImpl petDaoImpl;
//    = new PetDaoImpl();


    @Before
    public void init(){
        long owner_id = 14;

        a = new Cat();
        Pet pet = petDaoImpl.getPetsByName("Shy").get(0);
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
        if(a.getName()!=null) {
            catDaoImpl.delete(a.getId());
        }
       catDaoImpl = null;
       assertNull(catDaoImpl);
   }



    @Test
    public void saveTest() {
        String testName = "Yveltal";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Cat rio = catDaoImpl.getCatsByName(a.getName()).get(0);
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
        int expectedOfNumber = ;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getCatByNameTest(){
        String testName = "Leave";
        Cat test = null;
        if(catDaoImpl.getCatsByName("Leave")!=null) {
            test = catDaoImpl.getCatsByName("Leave").get(0);
        }

        assertEquals(testName, test.getName());
    }
}
