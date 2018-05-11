package org.jayne.crawler.dao;

import org.jayne.crawler.utils.DateConstants;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("UrlToSync")
public class UrlToSync {

    @Id
    private long id;

    @Column
    boolean start;

    @Column
    private String url;

    @Column
    private Date addTime = DateConstants.INITIAL_DATE;

    @Column
    private Date updateTime = DateConstants.INITIAL_DATE;

    @Column
    private String message = "";

    public UrlToSync() {
    }

    public UrlToSync(String url) {
        this.start = true;
        this.url = url;
        this.addTime = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
