package com.ascending.training;

import com.ascending.training.jdbc.DogDao;
import com.ascending.training.model.Dog;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DogDaoTest {

    private DogDao dogDao;

    @Before
    public void init(){
        dogDao = new DogDao();
    }

    @Test
    public void getIdofDogTest(){
        DogDao dogDao = new DogDao();
        List<Dog> dogs = dogDao.getDogs();
        int expectedIdofDog = 2;

        for(Dog dog : dogs) {
            System.out.print(dog);
        }

//        logger.info("");

        Dog dog = dogs.get(0);
        Assert.assertEquals(expectedIdofDog, dog.getId());

    }

    @After
    public void cleanUp(){
        dogDao = null;
    }
}
