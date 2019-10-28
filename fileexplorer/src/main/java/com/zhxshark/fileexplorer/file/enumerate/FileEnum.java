package com.zhxshark.fileexplorer.file.enumerate;

/**
 * @author zhuxin
 * @date 2019/10/21 16:17
 */
public enum  FileEnum {
    FILE_TAG_ENUM_MUSIC ("music","音乐","/images/file/music.png"),
    FILE_TAG_ENUM_MOVIE("movie","视频","/images/file/movie.png"),
    FILE_TAG_ENUM_PICTURE("photo","图片","/images/file/photo.png"),
    FILE_TAG_ENUM_WPS("wps","文档","/images/file/wps.png"),
    FILE_TAG_ENUM_ZIP("zip","压缩包","/images/file/zip.png"),
    FILE_TAG_ENUM_OTHER("others","其他","/images/file/others.png"),
    FILE_TAG_ENUM_FOLDER("folder","文件夹","/images/file/folder.png");

    private String tagEn;
    private String tagCh;
    private String fileImage;
    FileEnum(String tagEn, String tagCh, String fileImage) {
        this.tagEn = tagEn;
        this.tagCh = tagCh;
        this.fileImage = fileImage;
    }

//    public  static FileEnum getTagName(String ){
//        for(FileEnum f:FileEnum.values()){
//            if(f.getStatus() == index){
//                return f;
//            }
//        }
//        return null;
//    }

    public String getTagEn() {
        return tagEn;
    }

    public void setTagEn(String tagEn) {
        this.tagEn = tagEn;
    }

    public String getTagCh() {
        return tagCh;
    }

    public void setTagCh(String tagCh) {
        this.tagCh = tagCh;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }
}
