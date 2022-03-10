package com.guohaohome.moviedb.ossClient;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class OSSConfiguration {

    private volatile static OSSClientBuilder ossClientBuilder;
    private volatile static OSS ossClient;
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;

    @Value("${aliyun.bucket}")
    private String bucketName;

    @Value("${aliyun.endpoint}")
    public void setEndpoint(String np) {
        OSSConfiguration.endpoint = np;
    }

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String id) {
        OSSConfiguration.accessKeyId = id;
    }

    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String Secret) {
        OSSConfiguration.accessKeySecret = Secret;
    }

    public String getBucketName() {
        return bucketName;
    }


    public static OSSClientBuilder initOSSClientBuilder() {
        if (ossClientBuilder == null) {
            synchronized (OSSConfiguration.class) {
                if (ossClientBuilder == null) {
                    ossClientBuilder = new OSSClientBuilder();
                }
            }
        }
        return ossClientBuilder;
    }
    @Bean
    public OSS initOSSClient() {
        if (ossClient == null) {
            synchronized (OSSConfiguration.class) {
                if (ossClient == null) {
                    ossClient = initOSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }


}
