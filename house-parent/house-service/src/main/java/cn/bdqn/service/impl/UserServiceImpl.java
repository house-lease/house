package cn.bdqn.service.impl;

import cn.bdqn.domain.User;
import cn.bdqn.mapper.MoneyMapper;
import cn.bdqn.mapper.UserMapper;
import cn.bdqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

//    用户接口
    @Autowired
    private UserMapper userMapper;

    //可以资金接口
    @Autowired
    private MoneyMapper moneyMapper;

//    根据openid查询
    @Override
    public User queryByOpenId(String openId) {
        return userMapper.selectByOpenId(openId);
    }


    //根据用户id查询
    @Override
    public User queryByUserId(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void save(User user) {

        userMapper.insert(user);

    }

    @Override
    public User login(String code, String image_url, String nickName, Integer sex) {
        return null;
    }
}
