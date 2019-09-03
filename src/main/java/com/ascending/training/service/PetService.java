package com.ascending.training.service;

import com.ascending.training.model.User;
import com.ascending.training.model.Pet;
import com.ascending.training.repository.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetDao petDao;

    public boolean save(Pet pet) {
        return petDao.save(pet);
    }

    public boolean saveP(Pet p, User u){
        return petDao.savePet(p, u);
    }
    public int update(Pet pet){return petDao.update(pet);}
    public int delete(long petId){return petDao.delete(petId);}

    public List<Pet> getPets(){return petDao.getPets();}
    public List<Pet> getPetsByName(String petName){return petDao.getPetsByName(petName);}
    public Pet userGetPetById(long id){
        return petDao.userGetPetById(id);
    }
    public Pet catGetPetById(long id){
        return petDao.catGetPetById(id);
    }
    public Pet dogGetPetById(long id){
        return petDao.dogGetPetById(id);
    }
}
