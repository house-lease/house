package cn.bdqn.controller;

import cn.bdqn.domain.Collect;
import cn.bdqn.service.CollectService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 收藏功能的Controller
 */
@Controller
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService service;

    /**
     * 根据用户和房屋id添加记录
     * @param userId
     * @param houseId
     * @return
     */
    @RequestMapping("/addCollectRecord")
    @ResponseBody
    public Result addCollectRecord(Integer userId,Integer houseId) {

        //新建Result对象
        Result result = new Result();

        try {
            //根据用户id和房屋id添加收藏信息
            Collect collect = service.insertCollectByUserIdAndHouseId(userId, houseId);
            //设置返回信息为成功
            result.setData(collect);
            result.setMessage("添加成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //设置返回信息为失败
            result.setMessage("添加失败");
            return result;
        }



    }

    /**
     * 根据收藏id“删除”记录
     * @param collectId
     * @return
     */
    @RequestMapping("/deleteInfo")
    @ResponseBody
    public Result deleteInfo(Integer collectId) {

        //新建Result对象
        Result result = new Result();
        try{
            //根据收藏id删除信息
            service.deleteCollectByCollectId(collectId);
            result.setData(true);
            //设置返回信息为成功
            result.setMessage("删除成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            //设置返回信息为失败
            result.setData(false);
            result.setMessage("删除失败~");
            return result;
        }


    }

    /**
     * 根据用户id查询用户的收藏记录
     * @param userId
     * @return
     */
    @RequestMapping("/queryInfoByUserId")
    @ResponseBody
    public Result queryInfoByUserId(Integer userId) {

        //新建Result对象
        Result result = new Result();
        //调用service层的根据用户id查询该用户收藏记录的方法
        List<Collect> collects = service.queryInfoByUser_id(userId);
        //设置返回的数据
        result.setData(collects);
        //设置返回的信息
        result.setMessage("收藏信息");
        return result;

    }

    /**
     * 根据用户id查询用户的收藏记录
     * @param userId
     * @return
     */
    @RequestMapping("/queryInfoByUserIdAndHouseId")
    @ResponseBody
    public Result queryInfoByUserIdAndHouseId(Integer houseId,Integer userId) {

        //新建Result对象
        Result result = new Result();
        try {
            //调用service层的根据用户id查询该用户收藏记录的方法
            Collect collect = service.queryInfoByHouse_idAndUser_id(houseId,userId);
            //设置返回的数据
            result.setData(collect);
            //设置返回的信息
            result.setMessage("收藏信息");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询失败");
            return result;
        }
    }

}
