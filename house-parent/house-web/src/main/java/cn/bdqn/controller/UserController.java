package cn.bdqn.controller;

import cn.bdqn.domain.HouseImage;
import cn.bdqn.domain.Money;
import cn.bdqn.domain.User;
import cn.bdqn.service.UserService;
import cn.bdqn.utils.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;


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

//    短信验证
    @RequestMapping("/phoneVerification")
    @ResponseBody
    public Result phoneVerification(String phone){

        Result result = new Result();
        try {
//            发送验证码
            String  verification = userService.verification(phone);
            result.setData(verification);
            result.setMessage("成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(null);
            result.setMessage("失败~");
            return result;
        }
    }

//    绑定手机号的方法
    @RequestMapping("/bindingPhone")
    @ResponseBody
    public Result bindingPhone(Integer userId,String phone){

        Result result = new Result();
        try {
            User user = userService.bindingPhone(userId,phone);
            result.setData(user);
            result.setMessage("绑定成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("绑定失败");
            return result;
        }
    }

    /**
     * 查询用户可用资金
     * @param userId
     * @return
     */
    @RequestMapping("/queryMoney")
    @ResponseBody
    public Result queryMoney(Integer userId){
        Result result = new Result();
        try {
            Money money = userService.queryUserMoney(userId);
            result.setData(money);
            result.setMessage("查询成功！");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询失败");
            return result;
        }
    }

}
