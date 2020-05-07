package cn.bdqn.service.impl;

import cn.bdqn.domain.Chat;
import cn.bdqn.domain.User;
import cn.bdqn.mapper.ChatMapper;
import cn.bdqn.mapper.UserMapper;
import cn.bdqn.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChatMapper chatMapper;

    /**
     * 根据发送和接收的用户id还有发送消息添加记录
     */
    @Override
    public void addInfoBySendUserIdAndReceptionUserId(Integer sendUserId, Integer receptionUserId,String message) {

        //获取发送用户
        User sendUser = userMapper.selectByPrimaryKey(sendUserId);

        //获取接收用户
        User receptionUser = userMapper.selectByPrimaryKey(receptionUserId);

        //创建Chat对象
        Chat chat = new Chat();
        //设置发送消息内容
        chat.setMessage(message);
        //设置发送用户
        chat.setSendUser(sendUser);
        //设置接收用户
        chat.setReceptionUser(receptionUser);
        //设置发送时间
        chat.setSendTime(new Date());
        //设置记录状态
        chat.setState(0);

        //调用mapper层的添加方法
        chatMapper.insert(chat);

    }

    /**
     *根据发送和接收的用户id查看记录根据时间排序
     */
    @Override
    public List<Chat> selectAllInfoBySendUserIdAndReceptionUserId(Integer sendUserId, Integer receptionUserId) {


        //调用mapper层的根据发送和接收的用户id查看记录的方法
        List<Chat> lists = chatMapper.queryAllInfoBySendUserIdAndReceptionUserId(sendUserId,receptionUserId);

        return lists;
    }

    /**
     *根据记录id删除记录（list类型）
     */
    @Override
    public void deleteInfoByChatId(List<Integer> chatIds) {

        //调用mapper层的删除记录方法
        chatMapper.deleteInfoById(chatIds);

    }
}
