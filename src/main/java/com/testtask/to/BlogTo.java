package com.testtask.to;

public class BlogTo {

    private Integer id;

    private String title;

    private String content;

    public BlogTo() {
    }

    public BlogTo(Integer id) {
        this(id, null, null);
    }

    public BlogTo(String title, String content) {
        this(null, title, content);
    }

    public BlogTo(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public BlogTo(BlogTo orginal) {
        this(orginal.id, orginal.title, orginal.content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BlogTo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
