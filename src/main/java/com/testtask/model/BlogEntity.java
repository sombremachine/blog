package com.testtask.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;


@Entity
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
@Table(name = "posts")
public class BlogEntity implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BlogEntity that = (BlogEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public String toString() {
        return "BlogEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
