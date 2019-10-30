package com.zhxshark.fileexplorer.file.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhxshark.fileexplorer.commen.model.Page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.io.Serializable;

/**
 * @author zhuxin
 * @date 2019/10/21 16:05
 */
@Entity
public class ZxFile extends Page implements Serializable{

    @Id
    private String ObjectId; //mongodb中的编号

    private String fdId;  //编号

    private String fdParentId;  //父Id

    private String fdPersonId;  //创建人

    private String fdFileName;  //文件名

    private String fdFileType;  //文件类型

    private String fdFileTag;  //文件标签

    private String fdSize;  //文件大小

    private String fdFilePath;  //文件地址

    private String fdCreateTime;  //创建事件

    @Column(columnDefinition = "tinyint default 1")
    private Integer fdIsAvailable = 1;  //是否有效,1有效，0无效

    private String fdFileImagePath;  //文件图案地址

    //备用字段
    private String fdOne;
    private String fdTwo;
    private String fdThree;
    private String fdFour;
    private String fdFive;
    private String fdSix;
    private String fdSeven;
    private String fdEight;
    private String fdNine;
    private String fdTen;

    public ZxFile(String fdId, String fdParentId, String fdPersonId, String fdFileName, String fdFileType, String fdFileTag, String fdSize, String fdFilePath, String fdCreateTime, String fdFileImagePath) {
        this.fdId = fdId;
        this.fdParentId = fdParentId;
        this.fdPersonId = fdPersonId;
        this.fdFileName = fdFileName;
        this.fdFileType = fdFileType;
        this.fdFileTag = fdFileTag;
        this.fdSize = fdSize;
        this.fdFilePath = fdFilePath;
        this.fdCreateTime = fdCreateTime;
        this.fdFileImagePath = fdFileImagePath;
    }

    public ZxFile() {
    }

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId;
    }

    public String getFdParentId() {
        return fdParentId;
    }

    public void setFdParentId(String fdParentId) {
        this.fdParentId = fdParentId;
    }

    public String getFdPersonId() {
        return fdPersonId;
    }

    public void setFdPersonId(String fdPersonId) {
        this.fdPersonId = fdPersonId;
    }

    public String getFdFileName() {
        return fdFileName;
    }

    public void setFdFileName(String fdFileName) {
        this.fdFileName = fdFileName;
    }

    public String getFdFileType() {
        return fdFileType;
    }

    public void setFdFileType(String fdFileType) {
        this.fdFileType = fdFileType;
    }

    public String getFdFileTag() {
        return fdFileTag;
    }

    public void setFdFileTag(String fdFileTag) {
        this.fdFileTag = fdFileTag;
    }

    public String getFdSize() {
        return fdSize;
    }

    public void setFdSize(String fdSize) {
        this.fdSize = fdSize;
    }

    public String getFdFilePath() {
        return fdFilePath;
    }

    public void setFdFilePath(String fdFilePath) {
        this.fdFilePath = fdFilePath;
    }

    public String getFdCreateTime() {
        return fdCreateTime;
    }

    public void setFdCreateTime(String fdCreateTime) {
        this.fdCreateTime = fdCreateTime;
    }

    public Integer getFdIsAvailable() {
        return fdIsAvailable;
    }

    public void setFdIsAvailable(Integer fdIsAvailable) {
        this.fdIsAvailable = fdIsAvailable;
    }

    public String getFdFileImagePath() {
        return fdFileImagePath;
    }

    public void setFdFileImagePath(String fdFileImagePath) {
        this.fdFileImagePath = fdFileImagePath;
    }

    public String getFdOne() {
        return fdOne;
    }

    public void setFdOne(String fdOne) {
        this.fdOne = fdOne;
    }

    public String getFdTwo() {
        return fdTwo;
    }

    public void setFdTwo(String fdTwo) {
        this.fdTwo = fdTwo;
    }

    public String getFdThree() {
        return fdThree;
    }

    public void setFdThree(String fdThree) {
        this.fdThree = fdThree;
    }

    public String getFdFour() {
        return fdFour;
    }

    public void setFdFour(String fdFour) {
        this.fdFour = fdFour;
    }

    public String getFdFive() {
        return fdFive;
    }

    public void setFdFive(String fdFive) {
        this.fdFive = fdFive;
    }

    public String getFdSix() {
        return fdSix;
    }

    public void setFdSix(String fdSix) {
        this.fdSix = fdSix;
    }

    public String getFdSeven() {
        return fdSeven;
    }

    public void setFdSeven(String fdSeven) {
        this.fdSeven = fdSeven;
    }

    public String getFdEight() {
        return fdEight;
    }

    public void setFdEight(String fdEight) {
        this.fdEight = fdEight;
    }

    public String getFdNine() {
        return fdNine;
    }

    public void setFdNine(String fdNine) {
        this.fdNine = fdNine;
    }

    public String getFdTen() {
        return fdTen;
    }

    public void setFdTen(String fdTen) {
        this.fdTen = fdTen;
    }
}
