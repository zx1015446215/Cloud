package com.zhxshark.fileexplorer.commen.util;

/**
 * @author zhuxin
 * @date 2019/10/21 17:21
 */

import com.zhxshark.fileexplorer.file.enumerate.FileEnum;

import java.util.Arrays;

/**
 * 文件的工具类
 */
public class FileUtils {
    //图片
    private static String[] pics = {"JPG","JPEG","PNG","GIF","TIFF","BMP","DWG","PSD"};
    //文档
    private static String[] docs = {"DOC","RTF", "XML", "HTML", "CSS", "JS","EML", "DBX", "PST", "XLS_DOC", "XLSX_DOCX"
            , "VSD", "MDB", "WPS","WPD","EPS", "PDF","QDF", "PWL", "JSP", "JAVA,CLASS","DOCX",
            "MF","CHM","XLSX"};
    //视频
    private static String[] videos = {"AVI", "RAM", "RM", "MPG","MOV","ASF","MP4","FLV","MID" };
    //压缩包
    private static String[] zips = {"ZIP","JAR","CAB","GZ" ,"TAR","7Z","WAR","RAR"};
    //音乐
    private static String[] musics = {"WAV", "MP3" };

    /**
     * 判断文件类型
     */
    public static FileEnum JudgeFileType(String fileType) {
        fileType = fileType.toUpperCase();
        if(Arrays.asList(pics).contains(fileType)){  //若图片中存在
            return FileEnum.FILE_TAG_ENUM_PICTURE;
        }else if(Arrays.asList(docs).contains(fileType)){  //若文档中存在
            return FileEnum.FILE_TAG_ENUM_WPS;
        }else if(Arrays.asList(videos).contains(fileType)){ //若视频中存在
            return FileEnum.FILE_TAG_ENUM_MOVIE;
        }else if(Arrays.asList(zips).contains(fileType)) {  //若压缩包中存在
            return FileEnum.FILE_TAG_ENUM_ZIP;
        }else if(Arrays.asList(musics).contains(fileType)){  //若音乐中存在
            return FileEnum.FILE_TAG_ENUM_MUSIC;
        }else{  //在其它中
            return FileEnum.FILE_TAG_ENUM_OTHER;
        }
    }
}
