package cn.bdqn.controller;

import cn.bdqn.domain.User;
import cn.bdqn.service.UserService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {


    //用户业务层
    @Autowired
    private UserService userService;

    /**
     * 登录方法
     * @param code  临时令牌
     * @param image_url 图片路径
     * @param nickName  昵称
     * @param sex  性别
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String code, String image_url, String nickName, Integer sex){

        try {
            //登录的方法
            User user = userService.login(code,image_url,nickName,sex);
            Result result = new Result();
            result.setData(user);
            result.setMessage("登录成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            Result result = new Result();
            result.setData(null);
            result.setMessage("登录失败");
            return result;
        }

    }
}
