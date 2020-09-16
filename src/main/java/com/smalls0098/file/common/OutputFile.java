package com.smalls0098.file.common;

import com.smalls0098.file.enumeration.FileType;

import java.io.Serializable;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 10:37
 **/
public class OutputFile implements Serializable {

    private static final long serialVersionUID = -4312202356793333427L;

    private String name;

    private String path;

    private String md5;

    private String key;

    private FileType fileType;

    private Integer size;

    private String ext;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "OutputFile{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", md5='" + md5 + '\'' +
                ", key='" + key + '\'' +
                ", fileType=" + fileType +
                ", size=" + size +
                ", ext='" + ext + '\'' +
                '}';
    }
}
