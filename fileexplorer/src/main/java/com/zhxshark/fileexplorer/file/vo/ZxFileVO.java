package com.zhxshark.fileexplorer.file.vo;

import com.zhxshark.fileexplorer.file.model.ZxFile;

public class ZxFileVO extends ZxFile {
    private Integer pageSzie;
    private Integer pageNo;



    public Integer getPageSzie() {
        return pageSzie;
    }

    public void setPageSzie(Integer pageSzie) {
        this.pageSzie = pageSzie;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
