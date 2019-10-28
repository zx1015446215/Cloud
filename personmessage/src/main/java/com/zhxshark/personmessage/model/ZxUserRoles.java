package com.zhxshark.personmessage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class ZxUserRoles implements Serializable {
    private String fdId;

    private String fdCode;

    private String fdRoleId;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId == null ? null : fdId.trim();
    }

    public String getFdCode() {
        return fdCode;
    }

    public void setFdCode(String fdCode) {
        this.fdCode = fdCode == null ? null : fdCode.trim();
    }

    public String getFdRoleId() {
        return fdRoleId;
    }

    public void setFdRoleId(String fdRoleId) {
        this.fdRoleId = fdRoleId == null ? null : fdRoleId.trim();
    }
}