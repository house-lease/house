package cn.bdqn.mapper;

import cn.bdqn.domain.chatTest;

import java.util.List;

public interface chatTestMapper {
    int deleteByPrimaryKey(Integer id);


//    添加的方法
    void insert(chatTest record);


//    查询聊天记录
    public List<chatTest> selectByChatTest(Integer sendUserId,Integer receptionUserId);



    int insertSelective(chatTest record);

    chatTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(chatTest record);

    int updateByPrimaryKey(chatTest record);
}