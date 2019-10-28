package com.zhxshark.fileexplorer.file.service.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zhxshark.fileexplorer.commen.util.DateUtils;
import com.zhxshark.fileexplorer.commen.util.FileUtils;
import com.zhxshark.fileexplorer.commen.util.GenerateUtile;
import com.zhxshark.fileexplorer.commen.util.ObjectUtils;
import com.zhxshark.fileexplorer.file.enumerate.FileEnum;
import com.zhxshark.fileexplorer.file.mapper.ZxFileMapper;
import com.zhxshark.fileexplorer.file.model.ZxFile;
import com.zhxshark.fileexplorer.file.service.FileService;
import com.zhxshark.fileexplorer.file.vo.ZxFileVO;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhuxin
 * @date 2019/10/21 16:41
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.relativePath}")
    String fileRelativePath;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    ZxFileMapper zxFileMapper;

    @Override
    @Transactional
    public void uploadFile(List<MultipartFile> files, String fdParentId) throws IOException {
        String fdId;
        String fileName;
        String filePath;
        String fileType;
        String fileTag;
        String fileImagePath;
        String fdSize;
        String username = "zhuxin";
        String fdPersonId = "001";
        for (MultipartFile file : files){
            fileName = file.getOriginalFilename();  //文件名
            fileType = fileName.substring(fileName.lastIndexOf(".")+1).trim();  //文件类型
            FileEnum fdFileEnum = FileUtils.JudgeFileType(fileType);
            fileTag = fdFileEnum.getTagEn();   //文件标签
            filePath = ResourceUtils.getURL("classpath:").getPath()
                    +fileRelativePath+username+"/"+fileTag+"/"+fileName;  //文件路径
            fdId = GenerateUtile.getUUID();
            fdSize = String.valueOf(file.getSize());
            fileImagePath = fdFileEnum.getFileImage();
            ZxFile fdFile = new ZxFile(fdId,fdParentId,fdPersonId,fileName,fileType,fileTag,fdSize,filePath, DateUtils.getNowTimeString(3),fileImagePath);
            /**
             * 写入mongodb
             */
            ObjectId objectId = gridFsTemplate.store(file.getInputStream(), fileName, fileTag);
            /**
             * 属性放入mysql数据库中
             */
            fdFile.setObjectId(objectId.toString());
            zxFileMapper.insert(fdFile);
            //            /**
//             * 存入文件
//             */
//            File dest = new File(filePath);
//            if(!dest.getParentFile().exists()){
//                logger.warn("目录不存在，正在船舰目录:"+dest.getParentFile().getPath());
//                dest.getParentFile().mkdirs();  //新建文件夹
//            }
//            file.transferTo(dest);  //写入文件
//            /**
//             * 存入数据库
//             */
        }
    }

    @Override
    public void deleteFile(List<String> fdFileIds) {
        zxFileMapper.updateToInvalid(fdFileIds);
    }

    @Override
    @Transactional
    public void deleteFileForever(List<String> fdFileIds) {
        Iterator<String> iterator = fdFileIds.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            /**
             * mongodb中删除
             */
            gridFsTemplate.delete(new Query(Criteria.where("_id").is(new ObjectId(next))));
            /**
             * mysql中删除
             */
            zxFileMapper.deleteByIds(fdFileIds);
        }

    }

    @Override
    public void updateFile(ZxFile file) {
        zxFileMapper.updateByPrimaryKeySelective(file);
    }


    @Override
    public List<ZxFile> findFiles(ZxFileVO file) {
//        Query query = new Query();
//        Criteria criteria = new Criteria();
//        String[] fieldNames = ObjectUtils.getFieldNames(file);
//        for (int i=0 ;i<fieldNames.length ; i++){
//            Object value = ObjectUtils.getFieldValueByName(fieldNames[i], file);
//            if(value!=null){
//                criteria.and("metadata."+fieldNames[i]).is(value);
//            }
//        }
//        query.addCriteria(criteria);
//        GridFSFindIterable gridFSFiles = gridFsTemplate.find(query);
//        MongoCursor<GridFSFile> iterator = gridFSFiles.iterator();
//        List<ZxFile> list = new ArrayList<>();
//        while (iterator.hasNext()){
//            GridFSFile next = iterator.next();
//            Document metadata = next.getMetadata();
//            ZxFile zxFile = JSON.parseObject(metadata.toJson(), ZxFile.class);
//            zxFile.setObjectId(next.getObjectId().toString());
//            list.add(zxFile);
//        }
        /**
         * 从mysql中获取
         */
        List<ZxFile> zxFiles = zxFileMapper.selectByFile(file);
        return zxFiles;
    }
}
