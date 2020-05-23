package cn.bdqn.mapper;

import cn.bdqn.domain.ChatTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatTestMapper {
    int deleteByPrimaryKey(Integer id);


//    添加的方法
    void insert(ChatTest record);


//    查询聊天记录
    public List<ChatTest> selectByChatTest(@Param("sendUserId") Integer sendUserId, @Param("receptionUserId") Integer receptionUserId);

    int insertSelective(ChatTest record);

    ChatTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatTest record);

    int updateByPrimaryKey(ChatTest record);
}