package com.ascending.training.repository;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Dog;
import com.ascending.training.model.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class HibernateDogTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Dog a;
    private DogDaoImpl dogDaoImpl = new DogDaoImpl();
    private PetDaoImpl petDaoImpl = new PetDaoImpl();


    @Before
    public void init(){
//        long owner_id = 12;

        a = new Dog();
        Pet pet = petDaoImpl.getPetsByName("Elsa").get(0);
//        a.setId(30);
        a.setAdenovirus('N');
        a.setName("Jane");
//        a.setOwnerid(owner_id);
        a.setBordetella('N');
        a.setDistemper('Y');
        a.setParvo('Y');
        a.setRabies('N');
        a.setSpayNeuter('N');

        long id = pet.getId();


        dogDaoImpl.saveDog(a,id);
    }

    @After
    public void cleanUp(){
        dogDaoImpl.delete(a.getId());
        dogDaoImpl = null;
        assertNull(dogDaoImpl);
    }



    @Test
    public void saveTest() {
        String testName = "Elsa";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Dog leave = dogDaoImpl.getDogsByName("pigff").get(0);
        String newName = "Ameng";
        leave.setName(newName);
        dogDaoImpl.update(leave);

        assertEquals(leave.getName(), newName);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = dogDaoImpl.delete(a.getId());

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getDogsTest(){
        List<Dog> all = dogDaoImpl.getDogs();
        int expectedOfNumber = 2;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getDogByNameTest(){
        String testName = "Jinmu";
        Dog test = dogDaoImpl.getDogsByName(testName).get(0);

        assertEquals(testName, test.getName());
    }


}
