package com.security.chapter03.config;

import com.security.chapter03.Chapter03ApplicationTests;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

public class ValidateCodeBeanConfigTest extends Chapter03ApplicationTests{

    @Test
    public void imageCodeGenerator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/code/image"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void smsSender() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/code/sms").param("mobile","15999586003"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}