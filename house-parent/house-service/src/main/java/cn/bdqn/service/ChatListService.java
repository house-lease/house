package cn.bdqn.service;

import cn.bdqn.domain.ChatList;

import java.util.List;

public interface ChatListService {


    //  查询会话列表
    public ChatList queryByChatList(Integer sendUserId, Integer receptionUserId);

    //  查询会话列表
    public List<ChatList> queryAll(Integer userId);


    //    添加会话列表
    void save(ChatList record);

    //    更新会话列表
    void updateChat(ChatList record);

//    清空未读消息
    public void updateChatList(Integer sendUserId, Integer receptionUserId,Integer chatListId);
}
