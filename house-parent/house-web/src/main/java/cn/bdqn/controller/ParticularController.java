package cn.bdqn.controller;

import cn.bdqn.domain.Particular;
import cn.bdqn.exception.MyException;
import cn.bdqn.service.ParticularService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值记录请求的controller
 */
@Controller
@RequestMapping("/particular")
public class ParticularController {


    @Autowired
    private ParticularService particularService;
    //添加充值记录

    @RequestMapping("/save")
    public Result save(Particular record) {
        Result results = new Result<>();
        try {
            particularService.save(record);
            results.setMessage("添加成功~");
            return results;
        } catch (Exception e) {
            results.setMessage("添加失败~");
            e.printStackTrace();
            return results;
        }
    }

    /**
     * 根据用户id查询充值记录
     * @param userId
     * @return
     */
    @RequestMapping("/selectByUserId")
    @ResponseBody
    public Result selectByUserId(Integer userId){
        Result results = new Result<>();
        try {
            List<Particular> particulars = particularService.queryByUserId(userId);
            results.setData(particulars);
            results.setMessage("查询成功~");
            return results;
        }catch (Exception e){
            e.printStackTrace();
            results.setMessage("查询失败~");
            return results;
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Result results = new Result<>();
        try {
            //更新状态实现删除效果
            particularService.updateById(id);
            results.setMessage("删除成功~");
            return results;
        }catch (Exception e){
            e.printStackTrace();
            results.setMessage("删除失败~");
            return results;
        }

    }

    /**
     * 根据订单id查询
     * @param id
     * @return
     */
    @RequestMapping("/selectById")
    @ResponseBody
    public Result selectById(Integer id){

      Result result = new Result();
      try {
          Particular particular = particularService.queryByPrimaryKey(id);
          result.setData(particular);
          result.setMessage("查询成功~");
          return result;
      }catch (Exception e){
          e.printStackTrace();
          result.setMessage("查询失败~");
          return result;
      }
    }


}
