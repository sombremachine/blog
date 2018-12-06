package com.testtask.web;



import com.testtask.model.PostEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = BlogRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
//@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogRestController {
    static final String REST_URL = "/post";

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id) {
        return "id";
    }
//
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {

    }

    @GetMapping
    public List<String> getAll() {
        return Arrays.asList("asas","ddddd");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody PostEntity postEntity) {

    }
}
