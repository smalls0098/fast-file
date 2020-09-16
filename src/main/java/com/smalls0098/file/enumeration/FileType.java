package com.smalls0098.file.enumeration;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 11:18
 **/
public enum FileType {

    LOCAL(1, "本地上传"),
    QINIU(2, "七牛云上传"),
    ALIYUN(3, "阿里云上传");

    private Integer code;
    private String name;

    FileType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
