package cn.bdqn.service;

import cn.bdqn.domain.ChatTest;

import java.util.List;

public interface ChatTestService {


    //    添加的方法
    void save(ChatTest record);


    //    查询聊天记录
    public List<ChatTest> queryByChatTest(Integer sendUserId, Integer receptionUserId);
}
