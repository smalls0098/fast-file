package com.smalls0098.file.factory;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.smalls0098.file.AbstractUploader;
import com.smalls0098.file.configuration.AliyunConfiguration;
import com.smalls0098.file.enumeration.FileType;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 14:02
 **/
public class AliyunUploader extends AbstractUploader {

    public AliyunUploader(AliyunConfiguration aliyunConfiguration) {
        this.configuration = aliyunConfiguration;
    }

    @Override
    protected boolean saveFile(byte[] bytes, String newFilename, String key) {
        try {
            String absolutePath = Paths.get(this.configuration.getAssets(), getUploadPath(newFilename, key)).toString();
            absolutePath = absolutePath.replace("\\", "/");
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = this.configuration().getEndpoint();
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = this.configuration().getAccessKeyId();
            String accessKeySecret = this.configuration().getAccessKeySecret();
            String bucketName = this.configuration().getBucketName();

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, absolutePath, new ByteArrayInputStream(bytes));
            // 关闭OSSClient。
            ossClient.shutdown();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean deleteFile(String key) {
        try {
            String absolutePath = Paths.get(this.configuration.getAssets(), key).toString();
            absolutePath = absolutePath.replace("\\", "/");
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = this.configuration().getEndpoint();
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = this.configuration().getAccessKeyId();
            String accessKeySecret = this.configuration().getAccessKeySecret();

            String bucketName = this.configuration().getBucketName();
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.deleteObject(bucketName, absolutePath);
            // 关闭OSSClient。
            ossClient.shutdown();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected String getUploadPath(String newFilename, String key) {
        Date now = new Date();
        String format = new SimpleDateFormat("yyyy/MM/dd").format(now);
        String path = Paths.get(key, format, newFilename).toString();
        path = path.replace("\\", "/");
        return path;
    }

    @Override
    protected FileType getUploadType() {
        return FileType.ALIYUN;
    }

    private AliyunConfiguration configuration() {
        return (AliyunConfiguration) this.configuration;
    }


}
