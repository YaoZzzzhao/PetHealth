package com.ascending.training;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        com.ascending.training.UserDaoTest.class, com.ascending.training.PetDaoTest.class, com.ascending.training.CatDaoTest.class, com.ascending.training.DogDaoTest.class
})

public class TestAll {
}

