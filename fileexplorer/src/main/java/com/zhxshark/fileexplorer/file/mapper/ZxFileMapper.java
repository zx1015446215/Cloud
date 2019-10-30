package com.zhxshark.fileexplorer.file.mapper;

import com.zhxshark.fileexplorer.file.model.ZxFile;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@CacheNamespaceRef(ZxFileMapper.class)
public interface ZxFileMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(ZxFile record);

    int insertSelective(ZxFile record);

    ZxFile selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(ZxFile record);

    int updateByPrimaryKey(ZxFile record);

    int updateToInvalid(List<String> fdFileIds);

    int deleteByIds(List<String> fdFileIds);

    List<ZxFile> selectByPage(ZxFile file);
}