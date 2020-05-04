package cn.bdqn.service.impl;

import cn.bdqn.domain.Money;
import cn.bdqn.domain.User;
import cn.bdqn.mapper.MoneyMapper;
import cn.bdqn.mapper.UserMapper;
import cn.bdqn.service.UserService;
import cn.bdqn.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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

//    添加用户
    @Override
    public void save(User user) {
        //添加方法
        userMapper.insert(user);
        //设置用户资金对象
        Money money = new Money();
        money.setMoney(new BigDecimal(0));
        money.setUser(user);
        //条件用户资金
        moneyMapper.insert(money);
    }

//    登录方法
    @Override
    public User login(String code, String image_url, String nickName, Integer sex) {

        // 第一步、根据小程序登录,接收到code之后,需要请求微信服务器发送请求,
        //        登录凭证校验接口，需要携带appid,appsecret和code。
        String appid = "wx38fff06132aaab9d";
        String appsecret = "1bb2023a6363b97ac03b583b481c7fb2";
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appid +
                "&secret=" + appsecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";

        // 第二步、发送请求
        String responseData = HttpClientUtil.doGet(url);
        System.out.println(responseData);
        //转换为json
        JSONObject jsonObject = JSONObject.parseObject(responseData);
        //获得openid
        String openId = (String)jsonObject.get("openid");
        //根据openid查询用户
        User user = this.queryByOpenId(openId);
        if (user==null){
            User user1 = new User();
            user1.setNickname(nickName);
            user1.setSex(sex);
            user1.setOpenId(openId);
            user1.setImageUrl(image_url);
            user1.setRegisterTime(new Date());
            user1.setState(0);
            //添加新用户
            this.save(user1);
            return user1;
        }
        return user;
    }
}
