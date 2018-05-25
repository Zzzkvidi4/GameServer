package com.zzzkvidi4.gameserver.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.logging.Logger;

/**
 * Created by Роман on 24.05.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = HelloWorldController.class, secure = false)
public class HelloWorldControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void testGetInt() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/get_int")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        logger.info(result.getResponse().getContentAsString());
        String expected = "{value: 42}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }
}
