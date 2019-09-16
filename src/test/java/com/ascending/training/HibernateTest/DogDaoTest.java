package com.ascending.training;

import com.ascending.training.model.Dog;
import com.ascending.training.jdbc.DogDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class DogDaoTest{
    private DogDao dogDao;


    String toString(Dog u){
        return u.getName();
    }


    @Before
    public void init(){
        dogDao = new DogDao();
    }

    @After
    public void cleanup(){
//        dogDao.deleteDog()
        dogDao = null;
    }

    @Test
    public void getDogTest(){
        List<Dog> dogs = dogDao.getDogs();
        for (Dog t : dogs) {
//            assertEquals("",t.getDogname());
            System.out.println(t.getName());
//            System.out.println(t.toString());
        }
    }


    @Test
    public void insertDog(){
        Dog dog = new Dog();
        Date d = new Date(119,2,3);
        String name = "Carpe";
//        dog.setId(1);
        dog.setName(name);
        dog.setParvo('N');
        dog.setDistemper('Y');
        dog.setAdenovirus('Y');
        dog.setBordetella('Y');
        dog.setSpayNeuter('N');
        dog.setRabies('Y');


        dogDao.insertDog(dog);

        Assert.assertEquals(dog.getName(),name);


    }

    @Test
    public void updateDogTest(){
        Dog dog = new Dog();
        Date d = new Date(119,2,3);
        String name = "Diem";
//        dog.setId(1);
        dog.setName(name);
        dog.setParvo('N');
        dog.setDistemper('Y');
        dog.setAdenovirus('Y');
        dog.setBordetella('Y');
        dog.setSpayNeuter('N');
        dog.setRabies('Y');

        dogDao.updateDog(dog);

        Assert.assertEquals(dog.getName(),name);
    }

    @Test
    public void deleteDog(){
        int d = dogDao.deleteDog(1);

        Assert.assertEquals(d,1);
    }
}