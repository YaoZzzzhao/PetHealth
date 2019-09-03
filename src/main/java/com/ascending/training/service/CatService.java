package com.ascending.training.service;


import com.ascending.training.model.Cat;
import com.ascending.training.model.Pet;
import com.ascending.training.repository.CatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    @Autowired

    private CatDao catDao;

    public boolean save(Cat cat){
        return catDao.save(cat);
    }
    public boolean saveCat(Cat c, long id){
        return catDao.saveCat(c,id);
    }
    public int update(Cat cat){return catDao.update(cat);}
    public int delete(long catId){return catDao.delete(catId);}

    public List<Cat> getCats(){return catDao.getCats();}
    public List<Cat> getCatsByName(String catName){return catDao.getCatsByName(catName);}

}
