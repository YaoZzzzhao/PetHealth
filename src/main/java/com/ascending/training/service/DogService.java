package com.ascending.training.service;

import com.ascending.training.model.Dog;
import com.ascending.training.model.Pet;
import com.ascending.training.repository.DogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {
    @Autowired

    private DogDao dogDao;

    public boolean save(Dog dog){
        return dogDao.save(dog);
    }
    public boolean saveDog(Dog d, long id){
        return dogDao.saveDog(d,id);
    }
    public int update(Dog dog){return dogDao.update(dog);}
    public int delete(long dogId){return dogDao.delete(dogId);}

    public List<Dog> getDogs(){return dogDao.getDogs();}
    public List<Dog> getDogsByName(String dogName){return dogDao.getDogsByName(dogName);}
}
