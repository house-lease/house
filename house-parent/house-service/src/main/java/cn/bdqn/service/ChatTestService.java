package cn.bdqn.service;

import cn.bdqn.domain.ChatTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatTestService {


    //    添加的方法
    void save(ChatTest record);


    //    查询聊天记录
    public List<ChatTest> queryByChatTest(Integer sendUserId, Integer receptionUserId);

    //  查询未读消息条数
    public int queryViewState(Integer sendUserId, Integer receptionUserId);

    //    修改状态
    public void updateViewState(Integer sendUserId, Integer receptionUserId);
}
