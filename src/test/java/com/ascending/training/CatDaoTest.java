package com.ascending.training;

import com.ascending.training.jdbc.CatDao;
import com.ascending.training.model.Cat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CatDaoTest {

    private CatDao catDao;

    @Before
    public void init(){
        catDao = new CatDao();
    }

    @Test
    public void getCatsNameTest(){
        CatDao catdao = new CatDao();
        List<Cat> cat = catDao.getCats();
        String expectNameofCats = "Justin";

        for (Cat i : cat){
            System.out.print(i);
        }

        Cat cats = cat.get(0);
        Assert.assertEquals(expectNameofCats, cats.getName());
    }

}
