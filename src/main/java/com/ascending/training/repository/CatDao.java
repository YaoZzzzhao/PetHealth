package com.ascending.training.repository;

import com.ascending.training.model.Cat;
import com.ascending.training.model.Pet;

import java.util.List;

public interface CatDao {
    boolean save(Cat cat);
    boolean saveCat(Cat cat, long id);
    int update(Cat cat);
    int delete(long id);

    List<Cat> getCats();
    List<Cat> getCatsByName(String catName);
}
