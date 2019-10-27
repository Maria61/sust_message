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
 * @date 2019/10/26 23:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {
    @Autowired
    WebApplicationContext applicationContext;

    MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    /**
     * 测试通过留言id查看留言
     */
    @Test
    public void whenGetMessageById() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1.0/SUSTMessage/message/list/9"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试查看全部留言
     */
    @Test
    public void whenGetAllMessages() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1.0/SUSTMessage/message/lists"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试查看用户自己的已发布留言
     */
    @Test
    public void whenGetMessages() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1.0/SUSTMessage/user/201706060606/myMessages"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试按分类查看留言
     */
    @Test
    public void whenGetMessageByTypeId() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1.0/SUSTMessage/message/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试发布留言
     */
    @Test
    public void whenPublish() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1.0/SUSTMessage/user/201706060606/publish")
                        .param("messageContent","测试1")
                        .param("photo","")
                        .param("isAnonymous","0")
                        .param("messageTypeId","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试修改留言
     */
    @Test
    public void whenUpdate() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1.0/SUSTMessage/user/201706060606/myMessages/10")
                        .param("messageContent","测试2")
                        .param("photo","")
                        .param("isAnonymous","0")
                        .param("messageTypeId","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 删除留言
     */
    @Test
    public void whenDelete() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1.0/SUSTMessage/user/201706060606/myMessages/10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 给留言评论
     */
    @Test
    public void whenComment() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1.0/SUSTMessage/user/201706060606/9/comment")
                        .param("commentContent","这就是玄学")
                        .param("commentPhoto",""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试查看评论详情
     */
    @Test
    public void whenGetCommentByCommentId() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1.0/SUSTMessage/message/9/comments/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试删除评论
     */
    @Test
    public void whenDeleteComment() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1.0/SUSTMessage/user/201706060606/9/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 测试给留言点赞
     */
    @Test
    public void whenLike() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1.0/SUSTMessage/user/201706060606/9/like"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
