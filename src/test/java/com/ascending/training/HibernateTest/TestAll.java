package com.ascending.training.HibernateTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDaoTest.class, PetDaoTest.class, CatDaoTest.class, DogDaoTest.class
})

public class TestAll {

}

