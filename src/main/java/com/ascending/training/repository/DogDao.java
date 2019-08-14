package com.ascending.training.repository;

import com.ascending.training.model.Dog;

import java.util.List;

public interface DogDao {
    boolean save(Dog dog);
    int update(Dog dog);
    int delete(long id);

    List<Dog> getDog();
    Dog getDogById(long id);
}
