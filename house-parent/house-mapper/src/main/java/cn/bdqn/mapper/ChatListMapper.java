package cn.bdqn.mapper;

import cn.bdqn.domain.ChatList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatListMapper {


    //  查询会话列表
    public ChatList selectByChatList(@Param("sendUserId") Integer sendUserId, @Param("receptionUserId") Integer receptionUserId);

    //  查询会话列表
    public List<ChatList> selectAll(Integer userId);



//    添加会话列表
    void insert(ChatList record);


//查询
    ChatList selectByPrimaryKey(Integer id);


//    更新会话列表
    void updateByPrimaryKey(ChatList record);
}