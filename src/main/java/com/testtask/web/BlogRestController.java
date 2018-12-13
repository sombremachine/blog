package com.testtask.web;


import com.testtask.model.BlogEntity;
import com.testtask.service.BlogService;
import com.testtask.to.BlogTo;
import com.testtask.util.BlogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = BlogRestController.REST_URL)
public class BlogRestController {
    static final String REST_URL = "/post";

    private BlogService service;
    private BlogUtil util;

    @Autowired
    public BlogRestController(BlogService service, BlogUtil util) {
        this.service = service;
        this.util = util;
    }

    @GetMapping("/{id}")
    public BlogTo get(@PathVariable("id") int id) {
        return util.asTo(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @GetMapping
    public List<BlogTo> getAll() {
        return util.asTo(service.getAll());
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BlogEntity blogEntity, @PathVariable("id") int id) {
        service.update(blogEntity, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogTo create(@RequestBody BlogEntity blogEntity) {
        return util.asTo(service.create(blogEntity));
    }
}
