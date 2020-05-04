package cn.bdqn.mapper;

import cn.bdqn.domain.User;


/**
 * 用户接口
 */
public interface UserMapper {


    int deleteByPrimaryKey(Integer id);

//    根据id查询
    void insert(User record);

//    根据openid查询用户
    public User selectByOpenId(String openId);

    int insertSelective(User record);

//    根据用户id查询用户
    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}