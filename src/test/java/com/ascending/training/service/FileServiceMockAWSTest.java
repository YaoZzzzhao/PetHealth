package com.ascending.training.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.util.IOUtils;
import com.ascending.training.init.AppInitializer;
import org.junit.Before;
import org.junit.Test;
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

    private String bucketName = "practice-of-project";
    private String fileName = "test.txt";
    private URL fakeFileUrl;
    private MultipartFile multipartFile;
    private String path;


    @Before
    public void setUp() throws MalformedURLException, FileNotFoundException, IOException {
        logger.info(">>>>>>>> Start Testing......");
        MockitoAnnotations.initMocks(this);
        fakeFileUrl = new URL("http://www.fakeQueueUrl.com/aaa/aaa");
        File file = new File("/Users/yaozhao/Documents/Books/Thinking in Java/think-in-java.pdf");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(),"text/plain", IOUtils.toByteArray(input));
        path = System.getProperty("user.dir") + File.separator + "temp";

        when(amazonS3.doesObjectExist(anyString(), anyString())).thenReturn(false);
        when(amazonS3.generatePresignedUrl(any())).thenReturn(fakeFileUrl);
    }

    @Test
    public void getFileUrlTest(){

        FileService fileService = Mockito.mock(FileService.class);

        when(fileService.getFileUrl(anyString(),anyString())).thenReturn(anyString());

//        boolean isSuccess = fileService.getFileUrl(bucketName,fileName);

        verify(fileService, times(1)).getFileUrl(anyString(),anyString());
    }

}
