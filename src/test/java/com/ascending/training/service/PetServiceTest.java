package com.ascending.training.service;

import com.ascending.training.model.Pet;
import com.ascending.training.model.User;
import com.ascending.training.repository.UserDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PetServiceTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Pet a;
    private PetService petService = new PetService();
    private UserService userService = new UserService();


    @Before
    public void init(){
//        long owner_id = 12;
        String type = "DOG";
        String breed = "JinmuDog";
        String name = "pigff";
        int age = 3;
        String color = "Orange";

        a = new Pet();
//        a.setId(30);
        a.setName(name);
        a.setType(type);
        User user = userService.getUserById(6);
        a.setUser(user);

//        a.owner_id = a.getUser().getId();
        a.setAge(age);
        a.setBreed(breed);
        a.setColor(color);


        petService.saveP(a,user);
    }

    @After
    public void cleanUp(){
        petService.delete(a.getId());
        petService = null;
        assertNull(petService);
    }



    @Test
    public void savePetTest() {
        String testName = "pigff";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Pet leave = petService.getPetById(1);
        String newBreed = "Ameng";
        leave.setBreed(newBreed);
        petService.update(leave);

        assertEquals(leave.getBreed(), newBreed);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = petService.delete(1 );

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getUserTest(){
        List<Pet> all = petService.getPet();
        int expectedOfNumber =5 ;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getPetByIdTest(){
        long testId = 3;
        Pet test = petService.getPetById(testId);

        assertEquals(testId, test.getId());
    }



}
