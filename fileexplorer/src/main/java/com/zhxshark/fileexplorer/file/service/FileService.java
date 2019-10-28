package com.zhxshark.fileexplorer.file.service;

/**
 * @author zhuxin
 * @date 2019/10/21 16:22
 */

import com.zhxshark.fileexplorer.file.model.ZxFile;
import com.zhxshark.fileexplorer.file.vo.ZxFileVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 文件service
 */
public interface FileService {
    /**
     * 上传文件，个数不限
     * @param files
     */
    void uploadFile(List<MultipartFile> files, String fdParentId) throws IOException;

    /**
     * 暂时删除多个文件
     * @param fdFileIds
     */
    void deleteFile(List<String> fdFileIds);

    /**
     * 永久删除多个文件
     * @param fdFileIds
     */
    void deleteFileForever(List<String> fdFileIds);

    /**
     * 修改文件属性
     */
    void updateFile(ZxFile file);

    /**
     * 查找文件
     * @param file
     */
    List<ZxFile> findFiles(ZxFileVO file);


}
