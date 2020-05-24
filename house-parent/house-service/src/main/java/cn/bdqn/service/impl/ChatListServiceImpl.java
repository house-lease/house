package cn.bdqn.service.impl;

import cn.bdqn.domain.ChatList;
import cn.bdqn.mapper.ChatListMapper;
import cn.bdqn.service.ChatListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatListServiceImpl implements ChatListService {

    @Autowired
    private ChatListMapper chatListMapper;

    @Override
    public ChatList queryByChatList(Integer sendUserId, Integer receptionUserId) {
        return chatListMapper.selectByChatList(sendUserId,receptionUserId);
    }

    @Override
    public List<ChatList> queryAll(Integer userId) {
        return chatListMapper.selectAll(userId);
    }

    @Override
    public void save(ChatList record) {

        chatListMapper.insert(record);
    }

    @Override
    public void updateChat(ChatList record) {
        chatListMapper.updateByPrimaryKey(record);
    }
}
