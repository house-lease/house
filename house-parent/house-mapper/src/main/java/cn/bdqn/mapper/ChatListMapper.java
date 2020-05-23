package cn.bdqn.mapper;

import cn.bdqn.domain.ChatList;

import java.util.List;

public interface ChatListMapper {


    //  查询会话列表
    public ChatList selectByChatList(Integer sendUserId, Integer receptionUserId);

    //  查询会话列表
    public List<ChatList> selectAll(Integer userId);


    int deleteByPrimaryKey(Integer id);

//    添加会话列表
    void insert(ChatList record);


    int insertSelective(ChatList record);

    ChatList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatList record);

//    更新会话列表
    void updateByPrimaryKey(ChatList record);
}