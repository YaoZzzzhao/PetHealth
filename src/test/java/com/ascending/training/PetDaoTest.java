package com.ascending.training;

import com.ascending.training.model.Pet;
import com.ascending.training.jdbc.PetDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class PetDaoTest{
    private PetDao petDao;


    String toString(Pet u){
        return u.getName();
    }


    @Before
    public void init(){
        petDao = new PetDao();
    }

    @After
    public void cleanup(){
//        petDao.deletePet()
        petDao = null;
    }

    @Test
    public void getPetTest(){
        List<Pet> pets = petDao.getPets();
        for (Pet t : pets) {
//            assertEquals("",t.getPetname());
            System.out.println(t.getName());
//            System.out.println(t.toString());
        }
    }


    @Test
    public void insertPetTest(){
        Pet pet = new Pet();
        Date d = new Date(119,2,3);
        String name = "Steve";

//        User user = new User();
        pet.setId(1);
        pet.setName(name);
        pet.setBreed("Leave");
        pet.setColor("Orange");
        pet.setAge(2);
        pet.setType("CAT");


        petDao.insertPet(pet);

        Assert.assertEquals(pet.getName(),name);


    }

    @Test
    public void updatePetTest(){
        Pet pet = new Pet();
        Date d = new Date(119,2,3);
        String name = "Eileen";
        pet.setId(1);
        pet.setName(name);
        pet.setBreed("Leave");
        pet.setColor("Orange");
        pet.setAge(2);
        pet.setType("CAT");

        petDao.updatePet(pet);

        Assert.assertEquals(pet.getName(),name);
    }

    @Test
    public void deletePet(){
        int d = petDao.deletePet(1);

        Assert.assertEquals(d,1);
    }
}