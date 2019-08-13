package com.ascending.training.repository;

import com.ascending.training.model.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HibernatePetTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Pet a;
    private PetDaoImpl petDaoImpl = new PetDaoImpl();


    @Before
    public void init(){
        long owner_id = 12;
        String type = "DOG";
        String breed = "Jinmu";
        String name = "Leave";
        int age = 3;
        String color = "Orange";

        a = new Pet();
//        a.setId(30);
        a.setName(name);
//        a.setOwnerid(owner_id);
        a.setAge(age);
        a.setBreed(breed);
        a.setColor(color);


        petDaoImpl.save(a);
    }

    @After
    public void cleanUp(){
        petDaoImpl.delete(a.getId());
        petDaoImpl = null;
        assertNull(petDaoImpl);
    }



    @Test
    public void saveTest() {
        String testName = "Leave";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Pet leave = petDaoImpl.getPetById(16);
        String newBreed = "Ameng";
        leave.setBreed(newBreed);
        petDaoImpl.update(leave);

        assertEquals(leave.getBreed(), newBreed);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = petDaoImpl.delete(22 );

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getUserTest(){
        List<Pet> all = petDaoImpl.getPet();
        int expectedOfNumber = 17;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getPetByIdTest(){
        long testId = 12;
        Pet test = petDaoImpl.getPetById(testId);

        assertEquals(testId, test.getId());
    }


}
