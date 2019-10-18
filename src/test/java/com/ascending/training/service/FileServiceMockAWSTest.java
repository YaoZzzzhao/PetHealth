package com.ascending.training.service;

import com.amazonaws.services.cloudsearchdomain.model.Bucket;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.ascending.training.init.AppInitializer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceMockAWSTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private AmazonS3 amazonS3;
    @Autowired @Spy
    private Logger logger;

    @InjectMocks
    private FileService fileService;

    private String bucketName = "com.xiao.soccerproject";
    private String fileName = "fileServiceTest.txt";
    private URL fakeFileUrl;
    private MultipartFile multipartFile;
    private String path;


    @Before
    public void setUp() throws MalformedURLException, FileNotFoundException, IOException {
        logger.info(">>>>>>>> Start Testing......");
        MockitoAnnotations.initMocks(this);
        fakeFileUrl = new URL("http://www.fakeQueueUrl.com/aaa/aaa");
        File file = new File("./CICD/think-in-java.pdf");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(),"text/plain", IOUtils.toByteArray(input));
        path = System.getProperty("user.dir") + File.separator + "temp";

        when(amazonS3.doesObjectExist(anyString(), anyString())).thenReturn(false);
        when(amazonS3.generatePresignedUrl(any())).thenReturn(fakeFileUrl);
    }

    @After
    public void destroy(){
        logger.info(">>>>>Test Ends!");
    }

    @Test
    public void createBucketTest(){
        fileService.createBucket("Yzzz-test-bucket");
        verify(amazonS3,times(1)).createBucket(anyString());
    }

    @Test
    public void getFileUrlTest(){

//        AmazonS3 amazonS3 = Mockito.mock(AmazonS3.class);
        String fileUrl = fileService.getFileUrl(bucketName,fileName);

//        FileService f = Mockito.mock(FileService.class);
//
//        when(f.getFileUrl(anyString(),anyString())).thenReturn(anyString());

//        Assert.assertEquals(fileUrl,fakeFileUrl.toString());

        verify(amazonS3, times(1)).generatePresignedUrl(any());
    }

    @Ignore
    @Test
    public void getObjectTest(){
        S3Object obj = fileService.getObject(bucketName,fileName);
//        when(obj.)
        verify(amazonS3,times(1)).getObject(anyString(),anyString());
    }

//    @Test
//    public void getFilesListTest(){
//
//    }

    @Test
    public void getBucketListTest(){
//        FileService f = Mockito.mock(FileService.class);
        List<Bucket> bucketList = fileService.getBucketList();
//        when(fileService.getBucketList()).thenReturn(anyList());
        verify(amazonS3,times(1)).listBuckets();
    }

}
