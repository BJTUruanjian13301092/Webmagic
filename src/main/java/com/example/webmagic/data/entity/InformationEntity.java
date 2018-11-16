package com.example.webmagic.data.entity;

import java.io.Serializable;

public class InformationEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String title;

    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
