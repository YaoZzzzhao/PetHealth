package com.ascending.training;

import com.ascending.training.jdbc.PetsDao;
import com.ascending.training.model.Pets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetsDaoTest {

    private PetsDao petsDao;

    @Before
    public void init(){
        petsDao = new PetsDao();
    }

    @Test
    public void getPetNameTest(){
        PetsDao petsDao = new PetsDao();
        List<Pets> pets = petsDao.getPets();
        String expectedNameofPet = "Judd";

        for (Pets pet : pets){
            System.out.print(pet);
        }

        Pets pet = pets.get(0);
        Assert.assertEquals(expectedNameofPet, pet.getName());
    }

}
