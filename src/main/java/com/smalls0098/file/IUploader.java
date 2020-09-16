package com.smalls0098.file;

import com.smalls0098.file.common.InputFile;
import com.smalls0098.file.common.OutputFile;

import java.util.List;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 10:04
 **/
public interface IUploader {

    List<OutputFile> upload(List<InputFile> files, PreHandlerUploadListener preHandlerListener);

    List<OutputFile> upload(List<InputFile> files);

    List<OutputFile> upload(InputFile file, PreHandlerUploadListener preHandlerListener);

    List<OutputFile> upload(InputFile file);

    boolean delete(List<String> keys, PreHandlerDeleteListener preHandlerListener);

    boolean delete(List<String> keys);

    boolean delete(String key, PreHandlerDeleteListener preHandlerListener);

    boolean delete(String key);

}
