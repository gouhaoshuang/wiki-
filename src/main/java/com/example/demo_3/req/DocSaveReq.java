package com.example.demo_3.req;

import javax.validation.constraints.NotNull;

public class DocSaveReq {
    private Long id;
    @NotNull(message = "【电子书】不能为空")
    private Long ebookId;
    @NotNull(message = "【父文档】不能为空")
    private String parent;
    @NotNull(message = "【名称】不能为空")
    private String name;
    @NotNull(message = "【排序】不能为空")
    private String sort;

    private String viewCount;

    private String voteCount;

    private String content;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEbookId() {
        return ebookId;
    }

    public void setEbookId(Long ebookId) {
        this.ebookId = ebookId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }
    @Override
    public String toString() {
        return "DocSaveReq{" +
                "id=" + id +
                ", ebookId=" + ebookId +
                ", parent='" + parent + '\'' +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", voteCount='" + voteCount + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}