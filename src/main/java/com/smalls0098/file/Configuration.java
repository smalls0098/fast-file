package com.smalls0098.file;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 10:10
 **/
public abstract class Configuration {


    private String assets = "assets";

    private String uploadSize = "2MB";

    private Integer uploadCount = 5;

    private String[] includeExt = new String[]{
            ".png", ".jpg", ".gif", "jpeg"
    };

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public String getUploadSize() {
        return uploadSize;
    }

    public void setUploadSize(String uploadSize) {
        this.uploadSize = uploadSize;
    }

    public Integer getUploadCount() {
        return uploadCount;
    }

    public void setUploadCount(Integer uploadCount) {
        this.uploadCount = uploadCount;
    }

    public String[] getIncludeExt() {
        return includeExt;
    }

    public void setIncludeExt(String[] includeExt) {
        this.includeExt = includeExt;
    }
}
