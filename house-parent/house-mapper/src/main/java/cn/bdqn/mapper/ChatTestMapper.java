package cn.bdqn.mapper;

import cn.bdqn.domain.ChatTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatTestMapper {

//    添加的方法
    void insert(ChatTest record);


//    查询聊天记录
    public List<ChatTest> selectByChatTest(@Param("sendUserId") Integer sendUserId, @Param("receptionUserId") Integer receptionUserId);

//  查询未读消息条数
    public int selectViewState(@Param("sendUserId") Integer sendUserId, @Param("receptionUserId") Integer receptionUserId);


    ChatTest selectByPrimaryKey(Integer id);


//    修改状态
    void updateViewState(@Param("sendUserId") Integer sendUserId, @Param("receptionUserId") Integer receptionUserId);
}