package com.smalls0098.file.utils;

import java.util.UUID;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 10:56
 **/
public class UUIDUtil {

    /**
     * 最原始的UUID，带-
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取简单的UUID，不携带-
     *
     * @return uuid
     */
    public static String singleUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
