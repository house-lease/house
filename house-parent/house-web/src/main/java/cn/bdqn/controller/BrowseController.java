package cn.bdqn.controller;

import cn.bdqn.domain.Browse;
import cn.bdqn.service.BrowseService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 浏览功能的Controller
 */
@Controller
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private BrowseService service;

    /**
     * 根据用户id和房屋id添加浏览几率
     * @param userId
     * @param houseId
     * @return
     */
    @RequestMapping("/addInfo")
    @ResponseBody
    public Result addInfo(Integer userId,Integer houseId){

        //新建Result对象
        Result result = new Result();

        try {
            //调用service层的根据用户id和房屋id添加浏览记录的方法
            service.insertBrowseByUserIdAndHouseId(userId,houseId);

            //设置返回消息为成功
            result.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();

            //设置返回消息为失败
            result.setMessage("添加成功");
        }

        return result;
    }

    /**
     * 根据记录id“删除记录”
     * @param browseId
     * @return
     */
    @RequestMapping("/deleteInfo")
    @ResponseBody
    public Result deleteInfo(Integer browseId){

        //新建Result对象
        Result result = new Result();

        try{
            //调用service层的根据用户id和房屋id“删除”浏览记录的方法
            service.deleteBrowseByBrowseId(browseId);
            //设置返回消息为成功
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            //设置返回消息为失败
            result.setMessage("删除失败");
        }

        return result;
    }

    /**
     * 根据用户id查询全部的浏览记录
     * @return
     */
    @RequestMapping("/queryInfoByUserId")
    @ResponseBody
    public Result queryInfoByUserId(Integer userId){

        //新建Result对象
        Result result = new Result();

        //调用service层的根据用户id查询该用户全部浏览记录的方法
        List<Browse> browses = service.queryInfoByUser_id(userId);

        //设置返回数据
        result.setData(browses);

        //设置返回消息
        result.setMessage("浏览记录");

        return result;

    }

}
