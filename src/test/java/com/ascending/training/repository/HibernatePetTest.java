package com.ascending.training.repository;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.User;
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
public class HibernatePetTest {

    @Autowired
    Logger logger;
//            = LoggerFactory.getLogger(this.getClass());
    private Pet a;
    @Autowired
    private PetDaoImpl petDaoImpl;
//            = new PetDaoImpl();
    @Autowired
    private UserDaoImpl userDaoImpl;
//        = new UserDaoImpl();


    @Before
    public void init(){
//        long owner_id = 12;
        String type = "CAT";
        String breed = "AdoraCat";
        String name = "Yveltal";
        int age = 3;
        String color = "Orange";

        a = new Pet();
//        a.setId(30);
        a.setName(name);
        a.setType(type);
        User user = userDaoImpl.getUsersByName("Kite").get(0);
        a.setUser(user);

//        a.owner_id = a.getUser().getId();
        a.setAge(age);
        a.setBreed(breed);
        a.setColor(color);


        petDaoImpl.savePet(a, user);
    }

    @After
    public void cleanUp(){
        if(a.getName()!=null) {
            petDaoImpl.delete(a.getId());
        }
        petDaoImpl = null;
        assertNull(petDaoImpl);
    }


    @Test
    public void savePetTest() {
        String testName = "Yveltal";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Pet leave = petDaoImpl.getPetsByName(a.getName()).get(0);
        String newBreed = "Ameng";
        leave.setBreed(newBreed);
        petDaoImpl.update(leave);

        assertEquals(leave.getBreed(), newBreed);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = petDaoImpl.delete(a.getId());

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getPetsTest(){
        List<Pet> all = petDaoImpl.getPets();
        int expectedOfNumber =5;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getPetsByNameTest(){
        String testName = "pigff";
        Pet test = petDaoImpl.getPetsByName(testName).get(0);

        assertEquals(testName, test.getName());
    }


}
