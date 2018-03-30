package com.jxliu.calf.pojo;

import java.util.Date;

public class AuthoriRole {
    private Integer authorRoleId;

    private Integer authorId;

    private Integer roleId;

    private Date createtime;

    public Integer getAuthorRoleId() {
        return authorRoleId;
    }

    public void setAuthorRoleId(Integer authorRoleId) {
        this.authorRoleId = authorRoleId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}