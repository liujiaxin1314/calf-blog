package com.jxliu.calf.pojo;

import java.util.Date;

public class Authority {
    private Integer authorId;

    private String authorName;

    private Integer authorLevel;

    private Date createtime;

    private Date updatetime;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public Integer getAuthorLevel() {
        return authorLevel;
    }

    public void setAuthorLevel(Integer authorLevel) {
        this.authorLevel = authorLevel;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}