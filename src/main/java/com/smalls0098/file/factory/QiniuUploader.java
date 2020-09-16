package com.smalls0098.file.factory;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.smalls0098.file.AbstractUploader;
import com.smalls0098.file.configuration.QiniuConfiguration;
import com.smalls0098.file.enumeration.FileType;
import com.smalls0098.file.exception.QiniuAuthErrorException;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 12:55
 **/
public class QiniuUploader extends AbstractUploader {

    private final Auth auth;

    public QiniuUploader(QiniuConfiguration qiniuConfiguration) {
        this.configuration = qiniuConfiguration;
        this.checkConfig();
        this.auth = Auth.create(this.configuration().getAccessKey(), this.configuration().getSecretKey());
    }

    private void checkConfig() {
        if (this.configuration().getAccessKey() == null || this.configuration().getAccessKey().length() == 0) {
            throw new QiniuAuthErrorException("AccessKey不能为空");
        }
        if (this.configuration().getSecretKey() == null || this.configuration().getSecretKey().length() == 0) {
            throw new QiniuAuthErrorException("SecretKey不能为空");
        }
        if (this.configuration().getBucketName() == null || this.configuration().getBucketName().length() == 0) {
            throw new QiniuAuthErrorException("BucketName不能为空");
        }
    }

    @Override
    protected boolean saveFile(byte[] bytes, String newFilename, String key) {
        try {
            String absolutePath = Paths.get(this.configuration.getAssets(), getUploadPath(newFilename, key)).toString();
            absolutePath = absolutePath.replace("\\", "/");
            Long expireSeconds = this.configuration().getExpireSeconds();
            if (expireSeconds == null) {
                expireSeconds = 3600L;
            }
            String upToken = this.auth.uploadToken(this.configuration().getBucketName(), absolutePath, expireSeconds, this.configuration().getPutPolicy());
            //构造一个带指定Region对象的配置类
            Configuration cfg = new Configuration(Region.autoRegion());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            Response response = uploadManager.put(bytes, absolutePath, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            if (putRet.key == null || putRet.key.length() == 0) {
                return false;
            }
            return putRet.hash != null && putRet.hash.length() != 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean deleteFile(String key) {
        try {
            String absolutePath = Paths.get(this.configuration.getAssets(), key).toString();
            absolutePath = absolutePath.replace("\\", "/");
            //构造一个带指定Region对象的配置类
            Configuration cfg = new Configuration(Region.autoRegion());
            //...其他参数参考类注释
            BucketManager bucketManager = new BucketManager(auth, cfg);
            bucketManager.delete(this.configuration().getBucketName(), absolutePath);
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
        return FileType.QINIU;
    }

    private QiniuConfiguration configuration() {
        return (QiniuConfiguration) this.configuration;
    }

}
