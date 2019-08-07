package com.ascending.training;


import com.ascending.training.CatDaoTest;
import com.ascending.training.DogDaoTest;
import com.ascending.training.PetDaoTest;
import com.ascending.training.UserDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDaoTest.class, PetDaoTest.class, CatDaoTest.class, DogDaoTest.class
})

public class TestAll {
}

