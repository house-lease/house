package cn.bdqn.mapper;

import cn.bdqn.domain.chatList;
import cn.bdqn.domain.chatTest;

import java.util.List;

public interface chatListMapper {


    //  查询会话列表
    public chatList selectByChatList(Integer sendUserId, Integer receptionUserId);

    //  查询会话列表
    public List<chatList> selectAll(Integer userId);


    int deleteByPrimaryKey(Integer id);

//    添加会话列表
    void insert(chatList record);


    int insertSelective(chatList record);

    chatList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(chatList record);

//    更新会话列表
    void updateByPrimaryKey(chatList record);
}