package com.fehead.sustmessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/25 19:06
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {


    @Autowired
    WebApplicationContext applicationContext;

    MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    /**
     * 测试用户登录
     */
    @Test
    public void whenLogin() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1.0/SUSTMessage/user/login/201706060606/201706"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
    /**
     * 测试用户注册
     */
    @Test
    public void whenRegister() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1.0/SUSTMessage/user/register")
                        .param("studentId","201704050606")
                        .param("telephone","15829434543")
                        .param("password","45673")
                        .param("displayName","345456")
                        .param("avatar",""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试通过用户id查找用户
     */
    @Test
    public void whenGetUserById() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1.0/SUSTMessage/user/201706060612/info"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

}
