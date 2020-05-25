package cn.bdqn.service.impl;

import cn.bdqn.domain.ChatList;
import cn.bdqn.domain.ChatTest;
import cn.bdqn.mapper.ChatListMapper;
import cn.bdqn.service.ChatListService;
import cn.bdqn.service.ChatTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatListServiceImpl implements ChatListService {

    @Autowired
    private ChatListMapper chatListMapper;

    @Autowired
    private ChatTestService chatTestService;
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

    @Override
    public void updateChatList(Integer sendUserId, Integer receptionUserId, Integer chatListId) {
//        清空未读消息条数
        chatTestService.updateViewState(sendUserId,receptionUserId);

//        查询未读消息条数
        Integer count = chatTestService.queryViewState(sendUserId,receptionUserId);

//        根据id查询会话列表
        ChatList chatList = chatListMapper.selectByPrimaryKey(chatListId);
//        设置未读条数
        chatList.setUnread(count);

//        更新状态
        chatListMapper.updateByPrimaryKey(chatList);
    }
}
