package com.testtask.web;

import com.testtask.Launcher;
import com.testtask.containers.PostgresContainerRunner;
import com.testtask.containers.TestApplicationInitializer;
import com.testtask.service.request.BlogCreateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Launcher.class}, initializers = TestApplicationInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class BlogRestControllerTest {
    private static final String REST_URL = BlogRestController.REST_URL + '/';

    static {
        PostgresContainerRunner.run();
    }

    @LocalServerPort
    int serverPort;

    @Before
    public void initRestAssured() {
        RestAssured.port = serverPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void createMustWork() {
        BlogCreateRequest request = new BlogCreateRequest();
        request.setTitle("test_title");
        request.setContent("test_content");

        given()
                .contentType(ContentType.JSON)
                .body(request)
        .when()
                .post(REST_URL)
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("size()", is(3),
                        "id", is(not(empty())),
                        "title", is("test_title"),
                        "content", is("test_content")
                );

    }

    @Test
    public void getAllMustWork() {
        when()
                .get(REST_URL)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(2),
                        "id", is(not(empty())),
                        "title", hasItems("title_post_1000", "title_post_1001"),
                        "content", hasItems("content_post_1000", "content_post_1001")
                );
    }

    @Test
    public void getMustWork() {
        when()
                .get(REST_URL + "{id}", 1000)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(1000),
                        "title", is("title_post_1000"),
                        "content", is("content_post_1000")
                );
    }

    @Test
    public void updateMustWork() {
        List<Integer> ids = get(REST_URL).path("id");

        BlogCreateRequest request = new BlogCreateRequest();
        request.setTitle("test_title_updated");
        request.setContent("test_content_updated");

        given()
                .contentType(ContentType.JSON)
                .body(request)
        .when()
                .put(REST_URL + "{id}", ids.get(0))
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", is(ids.get(0)),
                        "title", is("test_title_updated"),
                        "content", is("test_content_updated")
                );
        when()
                .get(REST_URL + "{id}", ids.get(0))
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", is(ids.get(0)),
                        "title", is("test_title_updated"),
                        "content", is("test_content_updated")
                );
    }


}