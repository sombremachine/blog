package com.testtask.service;

import com.testtask.model.BlogEntity;
import com.testtask.repository.BlogEntryRepository;
import com.testtask.service.request.BlogCreateRequest;
import com.testtask.service.response.BlogTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogEntryRepository repository;
    private final BlogMapper mapper;

    @Autowired
    public BlogServiceImpl(BlogEntryRepository repository, BlogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public BlogTo create(BlogCreateRequest blogCreateRequest) {
        return mapper.toBlogTo(repository.save(mapper.toBlogEntity(blogCreateRequest)));
    }

    @Transactional
    @Override
    public BlogTo update(BlogCreateRequest blogCreateRequest, int id) {
        BlogEntity blogEntity = repository.getOne(id);
        mapper.toBlogEntity(blogEntity, blogCreateRequest);
        return mapper.toBlogTo(repository.save(blogEntity));
    }

    @Transactional(readOnly = true)
    @Override
    public BlogTo get(int id) {
        return mapper.toBlogTo(repository.getOne(id));
    }

    @Transactional
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BlogTo> getAll() {
        return mapper.toBlogTo(repository.findAll());
    }
}
