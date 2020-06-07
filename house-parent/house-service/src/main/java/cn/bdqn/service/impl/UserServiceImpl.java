package cn.bdqn.service.impl;

import cn.bdqn.domain.Money;
import cn.bdqn.domain.Ocr;
import cn.bdqn.domain.User;
import cn.bdqn.mapper.MoneyMapper;
import cn.bdqn.mapper.UserMapper;
import cn.bdqn.service.UserService;
import cn.bdqn.utils.HttpClientUtil;
import cn.bdqn.utils.HttpUtils;
import cn.bdqn.utils.UrlUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        if(openId!=null){
            User user = this.queryByOpenId(openId);
            if (user==null){
                User user1 = new User();
                user1.setNickname(nickName);
                user1.setSex(sex);
                user1.setOpenId(openId);
                user1.setImageUrl(image_url);
                user1.setRegisterTime(new Date());
                user1.setState(0);
                user1.setLandlord(0);
                //添加新用户
                this.save(user1);
                return user1;
            }
            return user;
        }else {
            return null;
        }


    }


//    实名认证
    @Override
    public User authentication(Integer userId,String imageName) {
        String host = "https://ocr2idcard.market.alicloudapi.com";
        String path = "/OcridCard";
        String method = "POST";
        String appcode = "e5cd97bb1e904429a0daf5b9b616431b";//这里是阿里云的AppCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        //改这里
        //这里是图片路径
        bodys.put("image", UrlUtil.LINUX_URL+"/house/idCard/"+imageName);
        //或者base64
        //bodys.put("image", "data:image/jpeg;base64,........");   //jpg图片
        //bodys.put("image", "data:image/png;base64,........");   //png图片
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            String message = EntityUtils.toString(response.getEntity());
            System.out.println(message);
            //转换为json
            JSONObject jsonObject = JSONObject.parseObject(message);
            JSONObject ocr = JSONObject.parseObject(jsonObject.get("ocr").toString());
            String idCard =  (String) ocr.get("idCard");
            String name =  (String) ocr.get("name");
            System.out.println(idCard+"---"+name);
            User user = null;
            if (name!=null){
//            查询实名认证用户
                user = userMapper.selectByPrimaryKey(userId);
                user.setIdcard(idCard);
                user.setUserName(name);
                //修改
                userMapper.updateByPrimaryKey(user);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    短信验证
    @Override
    public String verification(String phone){

        String host = "https://feginesms.market.alicloudapi.com";
        String path = "/codeNotice";
        String method = "GET";
        String appcode = "a55b99e70e2b4860a0fe8056265719b8";//阿里云appCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
//        生成验证码
        Long param = Math.round((Math.random()+1) * 1000);
        //这里是验证码哈
        querys.put("param", param.toString());
        //然后这里是手机号
        querys.put("phone", phone);
        //签名编号【联系旺旺客服申请，测试请用1】
        querys.put("sign", "1");
        //模板编号【联系旺旺客服申请，测试请用1~21】
        querys.put("skin", "1");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 或者直接下载：
             * http://code.fegine.com/HttpUtils.zip
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             * 相关jar包（非pom）直接下载：
             * http://code.fegine.com/aliyun-jar.zip
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            String message = EntityUtils.toString(response.getEntity());
            System.out.println(message);
            //转换为json
            JSONObject jsonObject = JSONObject.parseObject(message);
            String ok =  (String) jsonObject.get("Message");
            if ("OK".equals(ok)){
                return param.toString();
            }else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    绑定手机号
    @Override
    public User bindingPhone(Integer userId, String phone) {

        User user = this.queryByUserId(userId);
        user.setPhone(phone);
//        更新用户信息
        userMapper.updateByPrimaryKey(user);
//        返回最新用户信息
        return user;
    }

//    查询该用户可用余额
    @Override
    public Money queryUserMoney(Integer userId) {
//        查询该用户可用账户余额
        Money money = moneyMapper.selectByUerId(userId);
        return money;
    }
}
