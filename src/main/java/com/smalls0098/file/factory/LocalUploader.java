package com.smalls0098.file.factory;

import com.smalls0098.file.enumeration.FileType;
import com.smalls0098.file.AbstractUploader;
import com.smalls0098.file.configuration.LocalConfiguration;
import com.smalls0098.file.utils.FileUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 10:04
 **/
public class LocalUploader extends AbstractUploader {


    public LocalUploader(LocalConfiguration localConfiguration) {
        this.configuration = localConfiguration;
    }

    public LocalUploader() {
        this.configuration = new LocalConfiguration();
    }

    @Override
    protected boolean saveFile(byte[] bytes, String newFilename, String key) {
        try {
            String absolutePath = FileUtil.getFileAbsolutePath(this.configuration.getAssets(), getUploadPath(newFilename, key));
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(absolutePath)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean deleteFile(String key) {
        try {
            String absolutePath = FileUtil.getFileAbsolutePath(this.configuration.getAssets(), key);
            File file = new File(absolutePath);
            if (!file.exists()) {
                return false;
            }
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected String getUploadPath(String newFilename, String key) {
        Date now = new Date();
        String keyPath = Paths.get(this.configuration.getAssets(), key).toString();
        String format = new SimpleDateFormat("yyyy/MM/dd").format(now);
        Path path = Paths.get(keyPath, format).toAbsolutePath();
        File file = new File(path.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        String resPath = Paths.get(key, format, newFilename).toString();
        resPath = resPath.replace("\\", "/");
        return resPath;
    }

    @Override
    protected FileType getUploadType() {
        return FileType.LOCAL;
    }

}
