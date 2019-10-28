package com.zhxshark.personmessage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class ZxRoles implements Serializable {
    private String fdId;

    private String fdRoleId;

    private String fdRoleName;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId == null ? null : fdId.trim();
    }

    public String getFdRoleId() {
        return fdRoleId;
    }

    public void setFdRoleId(String fdRoleId) {
        this.fdRoleId = fdRoleId == null ? null : fdRoleId.trim();
    }

    public String getFdRoleName() {
        return fdRoleName;
    }

    public void setFdRoleName(String fdRoleName) {
        this.fdRoleName = fdRoleName == null ? null : fdRoleName.trim();
    }
}