package com.notebook.oracle.version1;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;
    private Gson gson = new Gson();
    private MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/execute")
            .contentType(new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")));


    @Test
    public void simpleJsExpressionTest() throws Exception {

        Request body = new Request("%js 1 + 8", "9");

        Result result = new Result("9");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(result)));
    }

    @Test
    public void simplePythonExpressionTest() throws Exception {

        Request body = new Request("%python 1 + 8", "9");

        Result result = new Result("9");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(result)));
    }

    @Test
    public void simpleRubyExpressionTest() throws Exception {

        Request body = new Request("%ruby 1 + 8", "9");

        Result result = new Result("9");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(result)));
    }

    @Test
    public void simpleRExpressionTest() throws Exception {

        Request body = new Request("%R 1 + 8", "9");

        Result result = new Result("[1] 9");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(result)));
    }

    @Test
    public void variableAndStateTest() throws Exception {

        Request body = new Request("%js var x=9", "1");

        Result result = new Result("");



        MockHttpServletRequestBuilder session = builder.session(new MockHttpSession());

        this.mockMvc.perform(session.content(gson.toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(result)));

        body = new Request("%js x", "1");

        result = new Result("9");

        this.mockMvc.perform(session.content(gson.toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(result)));
    }

    @Test
    public void parseErrorTest() throws Exception {

        Request body = new Request("%js.varx=9", "1");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().is(400));

    }

    @Test
    public void languageNotSupportedTest() throws Exception {

        Request body = new Request("%bad 9", "1");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().is(400));
    }

    @Test
    public void syntaxErrorTest() throws Exception {

        Request body = new Request("%python 9:,!", "1");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().is(400));

    }

    @Test
    public void infiniteRunningTest() throws Exception {

        Request body = new Request("%js while(true);", "1");

        this.mockMvc.perform(builder.content(gson.toJson(body)))
                .andExpect(status().is(400));

    }
}
