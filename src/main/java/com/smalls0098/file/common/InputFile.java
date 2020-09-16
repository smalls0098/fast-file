package com.smalls0098.file.common;

import java.io.Serializable;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 10:34
 **/
public class InputFile implements Serializable {

    private static final long serialVersionUID = 8170896949547632470L;

    private String originalFilename;

    private String name;

    private Integer size;

    private byte[] bytes;

    private String key;

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
