package cn.bdqn.service.impl;

import cn.bdqn.domain.ChatTest;
import cn.bdqn.mapper.ChatTestMapper;
import cn.bdqn.service.ChatTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatTestServiceImpl implements ChatTestService {

    @Autowired
    private ChatTestMapper chatTestMapper;

    @Override
    public void save(ChatTest record) {

        chatTestMapper.insert(record);
    }

    @Override
    public List<ChatTest> queryByChatTest(Integer sendUserId, Integer receptionUserId) {

        return chatTestMapper.selectByChatTest(sendUserId,receptionUserId);

    }
}
