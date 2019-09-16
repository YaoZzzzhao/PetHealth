package com.ascending.training.service;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.ascending.training.init.AppInitializer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class FileServiceTest {
//    @Autowired
//    Logger logger;
//
//    private String
    @Autowired
    private FileService fileService;

    @Test
    public void getBucketListTest(){
        Bucket tb = new Bucket("practice-of-project");

        List<Bucket> l = fileService.getBucketList();
        int size = 1;
        Assert.assertEquals(size,l.size());
    }

    @Test
    public void getObjectTest(){
        String bucketName = "practice-of-project";
        String S3key = "Algorithm.java";
//        fileService.getObject(bucketName,S3key);

        S3Object test = fileService.getObject(bucketName,S3key);
        Assert.assertNotNull(test);
    }

}
