package com.ascending.training.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileService {
    @Autowired
    private Logger logger;

    @Autowired
    private AmazonS3 amazonS3;

    public void createBucket(String bucketName){
        //Regions clientRegion =Regions.US_EAST_2;
        try{

            if(amazonS3.doesBucketExistV2(bucketName)){
                logger.info(String.format("The bucket %s has existed.", bucketName));
            }else{
                amazonS3.createBucket(bucketName);
//                String bucketLocation = s3Client.getBucketLocation(new GetBucketLocationRequest(bucketName));
                logger.info(String.format("Bucket %s is created", bucketName));
            }
        }catch(AmazonServiceException e){
            e.printStackTrace();
        }catch(SdkClientException e){
            e.printStackTrace();
        }
    }

    public List getBucketList(){
        List<Bucket> lb = amazonS3.listBuckets();
        return lb;
    }

    public void emptyBucket(String bucketName){


    }

    public void deleteBucket(String bucketName) {
        try {
            if (!amazonS3.doesBucketExistV2(bucketName)) {
                logger.info(String.format("The bucket %s does not exist", bucketName));
                return;
            }
            amazonS3.deleteBucket(bucketName);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }


    public String getFileUrl(String bucketName, String fileName){
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,fileName);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
        try {
            if (amazonS3.doesObjectExist(bucketName, file.getOriginalFilename())) {
                logger.info(String.format("The file '%s' exists in the bucket %s", file.getOriginalFilename(), bucketName));
                return null;
            }
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata);
            logger.info(String.format("The file name=%s, size=%d was uploaded to bucket %s", file.getOriginalFilename(), file.getSize(), bucketName));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return getFileUrl(bucketName, file.getOriginalFilename());
    }

    public boolean saveFile(MultipartFile multipartFile, String filePath){
        boolean isSuccess = false;

        try{
            File directory = new File(filePath);
            if(!directory.exists()) directory.mkdir();
            Path filepath = Paths.get(filePath, multipartFile.getOriginalFilename());
            multipartFile.transferTo(filepath);
            isSuccess = true;
            logger.info(String.format("This file %s is saved in the folder %s", multipartFile.getOriginalFilename(),filePath));
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

        return isSuccess;
    }

    public S3Object getObject(String bucketName, String S3key){
        return amazonS3.getObject(bucketName,S3key);
    }

    public List getFilesList(String bucketName){
        System.out.format("Objects in bucket %s:\n");

        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
//        for (S3ObjectSummary os : objects) {
//            System.out.println("* " + os.getKey());
//        }
        return objects;
    }

//    public Stream<String> getFile(){
//        BufferedReader
//    }


//    public void deleteFile()

}
