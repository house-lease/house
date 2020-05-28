package cn.bdqn.controller;

import cn.bdqn.domain.Apply;
import cn.bdqn.domain.HouseImage;
import cn.bdqn.domain.User;
import cn.bdqn.service.ApplyService;
import cn.bdqn.service.UserService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplyService applyService;


    @RequestMapping("/landlordApply")
    @ResponseBody
    public Result addInfo(Integer userId, MultipartFile houseImageUrl, HttpServletRequest request)throws Exception{

        //根据userId获取user信息
        User user = userService.queryByUserId(userId);
        Apply apply = new Apply();
        Result result = new Result();
        try{
            String url = "";
            // 1、得到文件上传的路径
            String path = request.getServletContext().getRealPath("/image/");
            File destPath = new File(path);
            if(!destPath.exists()){
                destPath.mkdirs();
            }
            // 获得原始名称
            String originalFilename = houseImageUrl.getOriginalFilename();
            // 文件上传
            houseImageUrl.transferTo(new File(destPath,originalFilename));
            //获取路径字符串
            url = "http://182.92.168.223/house/image/"+originalFilename;
            //添加对象属性
            apply.setUser(user);
            apply.setUserName(user.getUserName());
            apply.setUserPhone(user.getPhone());
            apply.setHouseImageUrl(url);
            apply.setState(1);
            apply.setCash(1);

            //向数据库添加认证信息
            applyService.addInfo(apply);
            result.setData(true);
            result.setMessage("上传成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(false);
            result.setMessage("上传失败~");
            return result;
        }

    }

}
