package com.testtask.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class BlogEntity{
    @Id
    @SequenceGenerator(name="seq",sequenceName="seq_id", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id")
    private Integer id;

    @Size(min = 2, max = 100)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(min = 2, max = 140)
    @Column(name = "content", nullable = false)
    private String content;

    public BlogEntity() {
    }

    public BlogEntity(Integer id) {
        this(id, null, null);
    }

    public BlogEntity(String title, String content) {
        this(null, title, content);
    }

    public BlogEntity(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public BlogEntity(BlogEntity orginal) {
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

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("content", content)
                .toString();
    }
}
