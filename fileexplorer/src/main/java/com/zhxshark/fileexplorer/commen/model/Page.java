package com.zhxshark.fileexplorer.commen.model;

/**
 * @author zhuxin
 * @date 2019/10/30 15:09
 */
public class Page {
    
    private Integer pageSize;
    private Integer pageNo;

    public Page() {
    }

    public Page(Integer pageSize, Integer pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
