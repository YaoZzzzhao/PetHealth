//package com.ascending.training.repository;
//
//import com.ascending.training.model.User;
//import com.ascending.training.model.Pet;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//public class HibernatePetTest {
//
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//    private Pet a;
//    private PetDaoImpl petDaoImpl = new PetDaoImpl();
//    private UserDaoImpl userDaoImpl = new UserDaoImpl();
//
//
//    @Before
//    public void init(){
////        long owner_id = 12;
//        String type = "CAT";
//        String breed = "AdoraCat";
//        String name = "Yveltal";
//        int age = 3;
//        String color = "Orange";
//
//        a = new Pet();
////        a.setId(30);
//        a.setName(name);
//        a.setType(type);
//        User user = userDaoImpl.getUsersByName("Kite").get(0);
//        a.setUser(user);
//
////        a.owner_id = a.getUser().getId();
//        a.setAge(age);
//        a.setBreed(breed);
//        a.setColor(color);
//
//
//        petDaoImpl.savePet(a, user);
//    }
//
////    @After
////    public void cleanUp(){
////        petDaoImpl.delete(a.getId());
////        petDaoImpl = null;
////        assertNull(petDaoImpl);
////    }
//
//
//    @Test
//    public void savePetTest() {
//        String testName = "Yveltal";
//
//
//        assertEquals(testName, a.getName());
//    }
//
//    @Test
//    public void updateTest(){
//        Pet leave = petDaoImpl.getPetsByName("Hoan").get(0);
//        String newBreed = "Ameng";
//        leave.setBreed(newBreed);
//        petDaoImpl.update(leave);
//
//        assertEquals(leave.getBreed(), newBreed);
//    }
//
//    @Test
//    public void deleteTest(){
//        int expectedOfNum = petDaoImpl.delete(1 );
//
//        assertEquals(1,expectedOfNum);
//    }
//
//    @Test
//    public void getPetsTest(){
//        List<Pet> all = petDaoImpl.getPets();
//        int expectedOfNumber =5 ;
//
//        assertEquals(all.size(),expectedOfNumber);
//    }
//
//    @Test
//    public void getPetsByNameTest(){
//        String testName = "Hoan";
//        Pet test = petDaoImpl.getPetsByName(testName).get(0);
//
//        assertEquals(testName, test.getName());
//    }
//
//
//}
