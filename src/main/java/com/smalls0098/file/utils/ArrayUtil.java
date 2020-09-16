package com.smalls0098.file.utils;

import java.util.Arrays;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/15 - 9:58
 **/
public class ArrayUtil {

    public static boolean arrayContainStr(final String[] arrays, final String str) {
        if (arrays == null || arrays.length == 0) {
            return true;
        }
        return Arrays.asList(arrays).contains(str);
    }

}
