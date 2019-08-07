package com.ascending.training.repository;

import com.ascending.training.model.Cat;

import java.util.List;

public interface CatDao {
    boolean save(Cat cat);
    boolean update(Cat cat);
    boolean delete(int catId);

    List<Cat> getCat();
    Cat getCatById(int catId);
}