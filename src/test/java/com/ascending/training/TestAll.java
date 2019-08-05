package com.ascending.training.jdbc;


import com.ascending.training.CatDaoTest;
import com.ascending.training.DogsDaoTest;
import com.ascending.training.PetsDaoTest;
import com.ascending.training.UsersDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UsersDaoTest.class, PetsDaoTest.class, CatDaoTest.class, DogsDaoTest.class
})

public class TestAll {
}

