package com.smalls0098.file.utils;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileUtil {

    public static String parseFileName(final String filename) {
        if (filename == null) {
            return "";
        }
        int lastIndex = filename.lastIndexOf(".");
        if (lastIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(lastIndex);
    }

    public static FileSystem getDefaultFileSystem() {
        return FileSystems.getDefault();
    }

    public static boolean isAbsolute(String str) {
        Path path = getDefaultFileSystem().getPath(str);
        return path.isAbsolute();
    }

    public static String getFileAbsolutePath(String dir, String filename) {
        if (isAbsolute(dir)) {
            return getDefaultFileSystem()
                    .getPath(dir, filename)
                    .toAbsolutePath().toString();
        } else {
            return getDefaultFileSystem()
                    .getPath(getCmd(), dir, filename)
                    .toAbsolutePath().toString();
        }
    }

    public static String getCmd() {
        return System.getProperty("user.dir");
    }


}
