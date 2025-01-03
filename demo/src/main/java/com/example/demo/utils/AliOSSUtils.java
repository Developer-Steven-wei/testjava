package com.example.demo.utils;

import java.io.*;
import java.util.Random;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSUtils {
    // private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // private String accessKeyId = "yourAccessKeyId";
    // private String accessKeySecret = "yourAccessKeySecret";
    // private String bucketName = "yourBucketName";
    // private String region = "cn-hangzhou";

    //读取配置文件
    // @Value("${aliyun.oss.endpoint}")
    // private String endpoint;

    // @Value("${aliyun.oss.bucketName}")
    // private String bucketName;

    // @Value("${aliyun.oss.region}")
    // private String region;

    private String endpoint;
    private String bucketName;
    private String region;

    /** 生成一个唯一的 Bucket 名称 */
    public static String generateUniqueBucketName(String prefix) {
        // 获取当前时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        // 生成一个 0 到 9999 之间的随机数
        Random random = new Random();
        int randomNum = random.nextInt(10000); // 生成一个 0 到 9999 之间的随机数
        // 连接以形成一个唯一的 Bucket 名称
        return prefix + "-" + timestamp + "-" + randomNum;
    }

    public String uplaodFile(String objectName, String filePath) throws Exception {
        String url = "";

        // 设置 OSS Endpoint 和 Bucket 名称
        String endpoint = this.endpoint;
        String bucketName = this.bucketName;
        // 替换为您的 Bucket 区域
        String region = this.region;
        // 创建 OSSClient 实例
        //echo "export OSS_ACCESS_KEY_ID='YOUR_ACCESS_KEY_ID'" >> ~/.bashrc
        //echo "export OSS_ACCESS_KEY_SECRET='YOUR_ACCESS_KEY_SECRET'" >> ~/.bashrc
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();// 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量。
        //CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret); // 使用默认的凭证访问方式。直接读取 accessKeyId 和 accessKeySecret。
        // 连接 OSS
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        try {
            // 1. 创建存储空间（Bucket）
            // 判断存储空间examplebucket是否存在。如果返回值为true，则存储空间存在，如果返回值为false，则存储空间不存在。
            boolean exists = ossClient.doesBucketExist(bucketName);
            if (!exists) {
                ossClient.createBucket(bucketName);
            }

            // 2. 上传文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            url = endpoint.split("//")[0] + bucketName + "." + endpoint.split("//")[1]  + "/" + objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}