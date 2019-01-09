package com.testtask.web;


import com.testtask.service.BlogService;
import com.testtask.service.request.BlogCreateRequest;
import com.testtask.service.response.BlogTo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Blog controller", description = "CRUD blog entities")
@RequestMapping(value = BlogRestController.REST_URL)
public class BlogRestController {
    static final String REST_URL = "/post";

    private BlogService service;

    @Autowired
    public BlogRestController(BlogService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public BlogTo getById(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping
    public List<BlogTo> getAll() {
        return service.getAll();
    }

    @PutMapping(value = "{id}")
    public BlogTo update(@RequestBody BlogCreateRequest blogCreateRequest, @PathVariable("id") int id) {
        return service.update(blogCreateRequest, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogTo create(@RequestBody BlogCreateRequest blogCreateRequest) {
        return service.create(blogCreateRequest);
    }
}
