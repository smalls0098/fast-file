package com.smalls0098.file;

import com.smalls0098.file.enumeration.FileType;
import com.smalls0098.file.common.InputFile;
import com.smalls0098.file.common.OutputFile;
import com.smalls0098.file.exception.FileExtensionErrorException;
import com.smalls0098.file.exception.FileTooManyException;
import com.smalls0098.file.exception.NotFoundException;
import com.smalls0098.file.utils.ArrayUtil;
import com.smalls0098.file.utils.FileUtil;
import com.smalls0098.file.utils.MD5Util;
import com.smalls0098.file.utils.UUIDUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 努力努力再努力！！！！！
 * Author：smalls
 * Github：https://github.com/smalls0098
 * Email：smalls0098@gmail.com
 * Date：2020/9/16 - 10:04
 **/
public abstract class AbstractUploader implements IUploader {

    private PreHandlerUploadListener preHandlerUploadListener;

    private PreHandlerDeleteListener preHandlerDeleteListener;

    protected Configuration configuration;

    /**
     * 文件上传
     *
     * @param files              文件列表
     * @param preHandlerListener 文件监听器
     * @return 输出文件列表
     */
    @Override
    public List<OutputFile> upload(List<InputFile> files, PreHandlerUploadListener preHandlerListener) {
        this.preHandlerUploadListener = preHandlerListener;
        return this.upload(files);
    }

    /**
     * 文件上传
     *
     * @param files 文件列表
     * @return 输出文件列表
     */
    @Override
    public List<OutputFile> upload(List<InputFile> files) {
        checkFiles(files);
        List<OutputFile> outputFiles = new ArrayList<>();
        this.handleUploadFiles(outputFiles, files);
        return outputFiles;
    }

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 输出文件列表
     */
    @Override
    public List<OutputFile> upload(InputFile file) {
        List<InputFile> inputFiles = new ArrayList<>();
        inputFiles.add(file);
        return this.upload(inputFiles);
    }

    /**
     * 文件上传
     *
     * @param file               文件
     * @param preHandlerListener 文件监听器
     * @return 输出文件列表
     */
    @Override
    public List<OutputFile> upload(InputFile file, PreHandlerUploadListener preHandlerListener) {
        this.preHandlerUploadListener = preHandlerListener;
        return this.upload(file);
    }

    /**
     * 处理文件列表
     *
     * @param outputFiles 输出文件列表
     * @param files       输入的文件列表
     */
    private void handleUploadFiles(List<OutputFile> outputFiles, List<InputFile> files) {
        for (InputFile file : files) {
            //解析后缀和检查后缀名
            String ext = FileUtil.parseFileName(file.getOriginalFilename());
            checkFileExt(ext);
            // 生成新的文件名
            String newFilename = this.getNewFilename(ext);
            // 获取新的文件路径
            String uploadPath = getUploadPath(newFilename, file.getKey());
            // 获取文件的字节数据
            byte[] bytes = file.getBytes();
            // 生成输出数据
            OutputFile outputFile = new OutputFile();
            outputFile.setExt(ext);
            outputFile.setKey(newFilename);
            outputFile.setMd5(MD5Util.encryptMD5ToString(bytes));
            outputFile.setName(file.getName());
            outputFile.setSize(bytes.length);
            outputFile.setFileType(getUploadType());
            outputFile.setPath(uploadPath);
            if (this.preHandlerUploadListener != null && !this.preHandlerUploadListener.handle(outputFile)) {
                return;
            }
            boolean result = saveFile(bytes, newFilename, file.getKey());
            if (result) {
                outputFiles.add(outputFile);
            }
        }
    }

    /**
     * 保存文件
     */
    protected abstract boolean saveFile(byte[] bytes, String newFilename, String key);

    /**
     * 删除文件
     */
    protected abstract boolean deleteFile(String key);

    /**
     * 获取上传路径
     */
    protected abstract String getUploadPath(String newFilename, String key);

    /**
     * 获取上传类型
     */
    protected abstract FileType getUploadType();

    /**
     * 获取新的文件名 - 通过UUID生成文件名
     */
    protected String getNewFilename(String ext) {
        String uuid = UUIDUtil.singleUuid();
        return uuid + ext;
    }

    /**
     * 检查文件后缀是否存在
     */
    protected void checkFileExt(String ext) {
        if (!ArrayUtil.arrayContainStr(this.configuration.getIncludeExt(), ext)) {
            throw new FileExtensionErrorException("文件格式错误，无法判断这个文件格式");
        }
    }

    /**
     * 检查文件列表是否合理
     */
    protected void checkFiles(List<InputFile> files) {
        if (files == null) {
            throw new NotFoundException("文件不存在无法进行上传处理");
        }
        if (files.isEmpty()) {
            throw new NotFoundException("文件不存在无法进行上传处理");
        }
        if (this.configuration.getUploadCount() < files.size()) {
            throw new FileTooManyException("您要上传的文件过多，请重新选择适当的文件数量");
        }
    }

    @Override
    public boolean delete(List<String> keys, PreHandlerDeleteListener preHandlerListener) {
        this.preHandlerDeleteListener = preHandlerListener;
        return delete(keys);
    }

    @Override
    public boolean delete(List<String> keys) {
        return this.handleDeleteFile(keys);
    }

    @Override
    public boolean delete(String key, PreHandlerDeleteListener preHandlerListener) {
        this.preHandlerDeleteListener = preHandlerListener;
        return delete(key);
    }

    @Override
    public boolean delete(String key) {
        List<String> keys = new ArrayList<>();
        keys.add(key);
        return delete(keys);
    }

    protected boolean handleDeleteFile(List<String> keys) {
        int i = 0;
        for (String key : keys) {
            if (this.preHandlerDeleteListener != null && !this.preHandlerDeleteListener.handle(key)) {
                continue;
            }
            boolean result = deleteFile(key);
            if (result) {
                i++;
            }
        }
        return i > 0;
    }

}
