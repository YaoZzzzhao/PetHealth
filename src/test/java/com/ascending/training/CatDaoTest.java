package com.ascending.training;

import com.ascending.training.model.Cat;
import com.ascending.training.jdbc.CatDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class CatDaoTest{
    private CatDao catDao;


    String toString(Cat u){
        return u.getName();
    }


    @Before
    public void init(){
        catDao = new CatDao();
    }

    @After
    public void cleanup(){
//        catDao.deleteCat()
        catDao = null;
    }

    @Test
    public void getCatTest(){
        List<Cat> cats = catDao.getCats();
        for (Cat t : cats) {
//            assertEquals("",t.getCatname());
            System.out.println(t.getName());
//            System.out.println(t.toString());
        }
    }


    @Test
    public void insertCat(){
        Cat cat = new Cat();
        Date d = new Date(119,2,3);
        String name = "Jjonak";
        cat.setId(1);
        cat.setName(name);
        cat.setRhi('Y');
        cat.setDeworm('Y');
        cat.setPan('Y');
        cat.setCalici('Y');
        cat.setSpayNeuter('N');
        cat.setRabies('Y');


        catDao.insertCat(cat);

        Assert.assertEquals(cat.getName(),name);


    }

    @Test
    public void updateCatTest(){
        Cat cat = new Cat();
        Date d = new Date(119,2,3);
        String name = "Poko";
        cat.setId(1);
        cat.setName(name);
        cat.setRhi('Y');
        cat.setDeworm('Y');
        cat.setPan('Y');
        cat.setCalici('Y');
        cat.setSpayNeuter('N');
        cat.setRabies('Y');

        catDao.updateCat(cat);

        Assert.assertEquals(cat.getName(),name);
    }

    @Test
    public void deleteCat(){
        int d = catDao.deleteCat(1);

        Assert.assertEquals(d,1);
    }
}