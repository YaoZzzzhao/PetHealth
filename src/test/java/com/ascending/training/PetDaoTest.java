package com.ascending.training;

import com.ascending.training.jdbc.PetDao;
import com.ascending.training.model.Pet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetDaoTest {

    private PetDao petsDao;

    @Before
    public void init(){
        petsDao = new PetDao();
    }

    @Test
    public void getPetNameTest(){
        PetDao petsDao = new PetDao();
        List<Pet> pets = petsDao.getPets();
        String expectedNameofPet = "Judd";

        for (Pet pet : pets){
            System.out.print(pet);
        }

        Pet pet = pets.get(0);
        Assert.assertEquals(expectedNameofPet, pet.getName());
    }

}
