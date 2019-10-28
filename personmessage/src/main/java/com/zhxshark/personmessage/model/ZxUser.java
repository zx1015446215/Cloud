package com.zhxshark.personmessage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@JsonIgnoreProperties(value = {"handler"})
public class ZxUser implements Serializable {
    private String fdId;

    private Date fdBirthday;

    private String fdCode;

    private String fdCreatePerson;

    private Date fdCreateTime;

    private String fdEmail;

    private String fdLoginName;

    private String fdName;

    private String fdPass;

    private String fdPhone;

    private String fdSex;

    private String fdUpdatePerson;

    private Date fdUpdateTime;

    private String fdIsavailable;

    private List<ZxRoles> roles;

    public List<ZxRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<ZxRoles> roles) {
        this.roles = roles;
    }

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId == null ? null : fdId.trim();
    }

    public Date getFdBirthday() {
        return fdBirthday;
    }

    public void setFdBirthday(Date fdBirthday) {
        this.fdBirthday = fdBirthday;
    }

    public String getFdCode() {
        return fdCode;
    }

    public void setFdCode(String fdCode) {
        this.fdCode = fdCode == null ? null : fdCode.trim();
    }

    public String getFdCreatePerson() {
        return fdCreatePerson;
    }

    public void setFdCreatePerson(String fdCreatePerson) {
        this.fdCreatePerson = fdCreatePerson == null ? null : fdCreatePerson.trim();
    }

    public Date getFdCreateTime() {
        return fdCreateTime;
    }

    public void setFdCreateTime(Date fdCreateTime) {
        this.fdCreateTime = fdCreateTime;
    }

    public String getFdEmail() {
        return fdEmail;
    }

    public void setFdEmail(String fdEmail) {
        this.fdEmail = fdEmail == null ? null : fdEmail.trim();
    }

    public String getFdLoginName() {
        return fdLoginName;
    }

    public void setFdLoginName(String fdLoginName) {
        this.fdLoginName = fdLoginName == null ? null : fdLoginName.trim();
    }

    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName == null ? null : fdName.trim();
    }

    public String getFdPass() {
        return fdPass;
    }

    public void setFdPass(String fdPass) {
        this.fdPass = fdPass == null ? null : fdPass.trim();
    }

    public String getFdPhone() {
        return fdPhone;
    }

    public void setFdPhone(String fdPhone) {
        this.fdPhone = fdPhone == null ? null : fdPhone.trim();
    }

    public String getFdSex() {
        return fdSex;
    }

    public void setFdSex(String fdSex) {
        this.fdSex = fdSex == null ? null : fdSex.trim();
    }

    public String getFdUpdatePerson() {
        return fdUpdatePerson;
    }

    public void setFdUpdatePerson(String fdUpdatePerson) {
        this.fdUpdatePerson = fdUpdatePerson == null ? null : fdUpdatePerson.trim();
    }

    public Date getFdUpdateTime() {
        return fdUpdateTime;
    }

    public void setFdUpdateTime(Date fdUpdateTime) {
        this.fdUpdateTime = fdUpdateTime;
    }

    public String getFdIsavailable() {
        return fdIsavailable;
    }

    public void setFdIsavailable(String fdIsavailable) {
        this.fdIsavailable = fdIsavailable == null ? null : fdIsavailable.trim();
    }
}