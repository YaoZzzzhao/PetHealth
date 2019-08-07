package com.ascending.training.repository;

import com.ascending.training.model.Dog;

import java.util.List;

public interface DogDao {
    boolean save(Dog dog);
    boolean update(Dog dog);
    boolean delete(int dogId);

    List<Dog> getDog();
    Dog getDogById(int dogId);
}
