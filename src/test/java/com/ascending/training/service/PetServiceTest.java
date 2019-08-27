package com.ascending.training.service;

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
@SpringBootTest(classes= AppInitializer.class)              //找AppInitializer中的scanBasePackage，测试package下的文件
public class PetServiceTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Pet a;

    @Autowired
    private PetService petService;
    @Autowired
    private UserService userService = new UserService();


    @Before
    public void init(){
//        long owner_id = 12;
        String type = "DOG";
        String breed = "JinmuDog";
        String name = "Shy";
        int age = 3;
        String color = "Orange";

        a = new Pet();
//        a.setId(30);
        a.setName(name);
        a.setType(type);

//        a.owner_id = a.getUser().getId();
        a.setAge(age);
        a.setBreed(breed);
        a.setColor(color);

        //User user = userService.getUserByName("Hoan").get(0);
        List<User> users = userService.getUsersByName("Jaygee");

        if (users == null || users.size() == 0) logger.info(">>>>>>> users is null or size is 0");
        User user = users.get(0);

        a.setUser(user);

        petService.saveP(a, user);
    }

    @After
    public void cleanUp(){
        petService.delete(a.getId());
        petService = null;
        assertNull(petService);
    }



    @Test
    public void savePetTest() {
        String testName = "Shy";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Pet pf = petService.getPetsByName("Pigff").get(0);
        String newName = "Sufei";
        pf.setName(newName);
        petService.update(pf);

        assertEquals(pf.getName(), newName);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = petService.delete(7);

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getPetTest(){
        List<Pet> all = petService.getPets();
        int expectedOfNumber =10 ;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getPetByNameTest(){
        String testName = "Shy";
        Pet test = petService.getPetsByName(testName).get(0);

        assertEquals(testName, test.getName());
    }



}
