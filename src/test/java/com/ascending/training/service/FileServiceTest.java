package com.ascending.training.service;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.ascending.training.init.AppInitializer;
import org.junit.Assert;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void getObjectTest(){
        String bucketName = "com.xiao.soccerproject";
        String S3key = "fileServiceTest.txt";
//        fileService.getObject(bucketName,S3key);

        S3Object test = fileService.getObject(bucketName,S3key);
        Assert.assertNotNull(test);
    }

    @Test
    public void createBucketTest(){
        String bucketName = "new-test-bucket222";
        fileService.createBucket(bucketName);
        List<Bucket> ls = fileService.getBucketList();
        int s = ls.size();
        ls.forEach(b -> System.out.println(b.getName()));
        Assert.assertEquals(s,2);
    }

    @Test
    public void deleteBucketTest(){
        String bucketName = "new-test-bucket222";
        fileService.deleteBucket(bucketName);
        int s =fileService.getBucketList().size();
        Assert.assertEquals(1,s);
    }



}
