package com.zhxshark.fileexplorer.file.service;

/**
 * @author zhuxin
 * @date 2019/10/21 16:22
 */

import com.mongodb.client.gridfs.model.GridFSFile;
import com.zhxshark.fileexplorer.file.model.ZxFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    List<ZxFile> findFiles(ZxFile file);

    /**
     * 下载文件
     * @param response
     * @param id
     */
    void downloadFile(HttpServletResponse response,String id) throws Exception;
}
