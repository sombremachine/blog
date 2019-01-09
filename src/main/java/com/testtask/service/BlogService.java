package com.testtask.service;

import com.testtask.service.request.BlogCreateRequest;
import com.testtask.service.response.BlogTo;

import java.util.List;

public interface BlogService {
    BlogTo create(BlogCreateRequest blogCreateRequest);

    BlogTo update(BlogCreateRequest blogCreateRequest, int id);

    BlogTo get(int id);

    void delete(int id);

    List<BlogTo> getAll();
}
