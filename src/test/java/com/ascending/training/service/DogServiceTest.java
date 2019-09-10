package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Dog;
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
public class DogServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Dog a;

    @Autowired
    private DogService dogService;

    @Autowired
    private PetService petService;


    @Before
    public void init(){
//        long owner_id = 12;

        a = new Dog();

        Pet pet = petService.getPetsByName("Elsa").get(0);
//        a.setId(30);
        a.setAdenovirus('N');
        a.setName("Elsa");
//        a.setOwnerid(owner_id);
        a.setBordetella('N');
        a.setDistemper('Y');
        a.setParvo('Y');
        a.setRabies('N');
        a.setSpayNeuter('N');
        long id = pet.getId();


        dogService.saveDog(a,id);
    }

//    @After
//    public void cleanUp(){
//        dogService.delete(a.getId());
//        dogService = null;
//        assertNull(dogService);
//    }



    @Test
    public void saveTest() {
        String testName = "Elsa";


        assertEquals(testName, a.getName());
    }

    @Test
    public void updateTest(){
        Dog yjjpp = dogService.getDogsByName("Elsa").get(0);
        String newName = "Ameng";
        yjjpp.setName(newName);
        dogService.update(yjjpp);

        assertEquals(yjjpp.getName(), newName);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = dogService.delete(a.getId());

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getDogsTest(){
        List<Dog> all = dogService.getDogs();
        int expectedOfNumber = 4;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getDogByNameTest(){
        String  testName = "Ameng";
        Dog test = dogService.getDogsByName(testName).get(0);

        assertEquals(testName, test.getName());
    }

}
