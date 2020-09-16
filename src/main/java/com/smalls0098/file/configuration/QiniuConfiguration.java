package com.smalls0098.file.configuration;

import com.qiniu.util.StringMap;
import com.smalls0098.file.Configuration;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 12:57
 **/
public class QiniuConfiguration extends Configuration {

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private String domain;

    private StringMap putPolicy;

    private Long expireSeconds = 3600L;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public StringMap getPutPolicy() {
        return putPolicy;
    }

    public void setPutPolicy(StringMap putPolicy) {
        this.putPolicy = putPolicy;
    }

    public Long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
