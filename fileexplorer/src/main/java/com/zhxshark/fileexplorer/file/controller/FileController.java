package com.zhxshark.fileexplorer.file.controller;

import com.zhxshark.fileexplorer.commen.model.JsonResult;
import com.zhxshark.fileexplorer.file.model.ZxFile;
import com.zhxshark.fileexplorer.file.service.FileService;
import com.zhxshark.fileexplorer.file.vo.ZxFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhuxin
 * @date 2019/10/21 16:41
 * 文件
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    /**
     * 上传文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResult upload(HttpServletRequest request,String fdParentId){
        logger.warn("进入upload");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        try {
            fileService.uploadFile(files,fdParentId);
        }catch (FileNotFoundException e){
            logger.warn("文件路径有错");
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }catch (IOException e){
            e.printStackTrace();
            logger.warn("文件写入出错");
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        } catch (Exception e){
            logger.warn("出错");
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
        return new JsonResult("上传成功");
    }

    /**
     * 暂时删除文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public JsonResult delete(HttpServletRequest request){
        logger.warn("进入delete");
        String[] fdIds = request.getParameter("fdIds").split(",");
        List<String> fdFileIds = Arrays.asList(fdIds);
        try {
            fileService.deleteFile(fdFileIds);
        }catch (Exception e){
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
        return new JsonResult("删除文件成功");
    }

    /**
     * 永久删除文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/foreverdelete", method = RequestMethod.GET)
    public JsonResult deleteForever(HttpServletRequest request){
        logger.warn("进入foreverdelete");
        String[] fdIds = request.getParameter("fdIds").split(",");
        List<String> fdFileIds = Arrays.asList(fdIds);
        try {
            fileService.deleteFileForever(fdFileIds);
        }catch (Exception e){
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
        return new JsonResult("删除文件成功");
    }

    /**
     * 修改文件属性
     * @param file
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult renameFile(ZxFile file){
        logger.warn("进入update");
        try {
            fileService.updateFile(file);
        }catch (Exception e){
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
        return new JsonResult("重命名成功");
    }

    /**
     * 按照条件查询文件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    public JsonResult find(@RequestBody(required = false) ZxFileVO file, HttpServletRequest request){
        logger.warn("进入find");
//        int pageNo = request.getAttribute("pageNo")==null?1:(Integer)request.getAttribute("pageNo");
//        int pageSize = request.getAttribute("pageSize")==null?5:(Integer)request.getAttribute("pageSize");
        List<ZxFile> files;
        try {
           files = fileService.findFiles(file);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
        return new JsonResult(files);
    }



}
