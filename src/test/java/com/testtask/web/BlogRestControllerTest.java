package com.testtask.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.Launcher;
import com.testtask.model.BlogEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import static com.testtask.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Launcher.class})
@WebAppConfiguration
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"))
public class BlogRestControllerTest {
    private static final String REST_URL = BlogRestController.REST_URL + '/';

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

    private String getJsonFromResult(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    private String getJsonFromResult(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    private BlogEntity jsonRead(String json) throws IOException {
        return MAPPER.readValue(json, BlogEntity.class);
    }

    private List<BlogEntity> jsonReadList(String json) throws IOException {
        return MAPPER.readerFor(BlogEntity.class).<BlogEntity>readValues(json).readAll();
    }

    private void testGetAll(List<BlogEntity> expected) throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(jsonReadList(getJsonFromResult(result))).isEqualTo(expected));
    }

    private void testGet(String url, BlogEntity expected) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(jsonRead(getJsonFromResult(result))).isEqualTo(expected));
    }

    @Test
    public void testGet() throws Exception {
        testGet(REST_URL + TOPIC2_ID, TOPIC2);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + TOPIC2_ID))
                .andExpect(status().isNoContent())
                .andDo(print());
        testGetAll(Arrays.asList(TOPIC1, TOPIC3));
    }

    @Test
    public void testgetAll() throws Exception {
        testGetAll(TOPICS);
    }

    @Test
    public void testCreate() throws Exception {
        BlogEntity expected = new BlogEntity(null, "New", "some content");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(expected)))
                .andExpect(status().isCreated())
                .andDo(print());

        BlogEntity returned = jsonRead(getJsonFromResult(action));
        expected.setId(returned.getId());
        assertThat(returned).isEqualTo(expected);
    }

    @Test
    public void testUpdtae() throws Exception {
        BlogEntity updated = new BlogEntity(TOPIC2);
        updated.setTitle("Updated");
        mockMvc.perform(put(REST_URL + TOPIC2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(updated)))
                .andExpect(status().isNoContent());
        testGet(REST_URL + "2", updated);
    }
}