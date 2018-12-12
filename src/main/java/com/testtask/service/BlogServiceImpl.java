package com.testtask.service;

import com.testtask.model.BlogEntity;
import com.testtask.repository.BlogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogEntryRepository repository;

    @Autowired
    public BlogServiceImpl(BlogEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public BlogEntity create(BlogEntity blogEntity) {
        return repository.save(blogEntity);
    }

    @Override
    public void update(BlogEntity blogEntity, int id) {
        blogEntity.setId(id);
        repository.save(blogEntity);
    }

    @Override
    public BlogEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<BlogEntity> getAll() {
        return repository.findAll();
    }
}
