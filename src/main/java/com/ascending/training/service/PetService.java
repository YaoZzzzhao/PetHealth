package com.ascending.training.service;

import com.ascending.training.model.Pet;
import com.ascending.training.repository.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PetService {

    @Autowired

    private PetDao petDao;

    public boolean save(Pet pet){
        return petDao.save(pet);
    }
    public int update(Pet pet){return petDao.update(pet);}
    public int delete(long petId){return petDao.delete(petId);}

    public List<Pet> getPet(){return petDao.getPet();}
    public Pet getPetById(long petId){return petDao.getPetById(petId);}
}
