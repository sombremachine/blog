package com.testtask.service;

import com.testtask.model.BlogEntity;
import java.util.List;

public interface BlogService {
    BlogEntity create(BlogEntity blogEntity);
    void update(BlogEntity blogEntity, int id);
    BlogEntity findById(int id);
    void    deleteById(int id);
    List<BlogEntity> getAll();
}
