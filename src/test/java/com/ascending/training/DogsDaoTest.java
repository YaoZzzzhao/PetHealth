package com.ascending.training;

import com.ascending.training.jdbc.DogsDao;
import com.ascending.training.model.Dogs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DogsDaoTest {

    private DogsDao dogsDao;

    @Before
    public void init(){
        dogsDao = new DogsDao();
    }

    @Test
    public void getIdofDogTest(){
        DogsDao dogsDao = new DogsDao();
        List<Dogs> dogs = dogsDao.getDogs();
        int expectedIdofDog = 2;

        for(Dogs dog : dogs){
            System.out.print(dog);
        }

        Dogs dog = dogs.get(0);
        Assert.assertEquals(expectedIdofDog, dog.getId());
    }
}
