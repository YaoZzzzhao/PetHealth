package com.ascending.training.service;

import com.ascending.training.model.Dog;
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
    public int update(Dog dog){return dogDao.update(dog);}
    public int delete(long dogId){return dogDao.delete(dogId);}

    public List<Dog> getDog(){return dogDao.getDog();}
    public Dog getDogById(long dogId){return dogDao.getDogById(dogId);}
}
