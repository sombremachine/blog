package com.testtask.web;


import com.testtask.model.BlogEntity;
import com.testtask.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = BlogRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogRestController {
    static final String REST_URL = "/post";

    @Autowired
    private BlogService service;

    @GetMapping("/{id}")
    public BlogEntity get(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @GetMapping
    public List<BlogEntity> getAll() {
        return service.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody BlogEntity blogEntity, @PathVariable("id") int id) {
        service.update(blogEntity, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogEntity> create(@RequestBody BlogEntity blogEntity) {
        //{TODO: check new}
        BlogEntity created = service.create(blogEntity);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
