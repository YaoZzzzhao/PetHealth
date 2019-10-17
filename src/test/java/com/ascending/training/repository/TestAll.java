package com.ascending.training.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HibernateCatTest.class, HibernateDogTest.class, HibernatePetTest.class, HibernateUserTest.class
})

public class TestAll {

}
