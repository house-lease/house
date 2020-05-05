package cn.bdqn.controller;

import cn.bdqn.domain.Particular;
import cn.bdqn.domain.User;
import cn.bdqn.exception.MyException;
import cn.bdqn.service.ParticularService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
    @ResponseBody
    public Result  save(Particular record) throws MyException {
        Result result = new Result();
        try {
            particularService.save(record);
            result.setMessage("充值成功");
        } catch (Exception e) {
            result.setMessage("充值失败去请稍后再试");
            e.printStackTrace();
            throw new MyException("充值失败");
        }
        return result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Result  delete(Integer id) throws MyException {
        Result<Map<String, Object>> results = new Result<>();
        Map<String, Object> result = new HashMap<>();

        try {
            particularService.delete(id);
            result.put("success", "删除成功");
        } catch (Exception e) {
            results.setMessage("删除失败去请稍后再试");
            e.printStackTrace();
            throw new MyException("删除失败");
        }
        results.setData(result);
        return results;
    }

    @RequestMapping("/select")
    @ResponseBody
    public Result selectById(Integer id) throws Exception {
        Result<Map<String, Object>> results = new Result<>();
        Map<String, Object> result = new HashMap<>();

        try {
            Particular particular=particularService.selecById(id);
            System.out.println("查询成功");
            result.put("success", "查询成功");
        } catch (Exception e) {
            results.setMessage("查询失败去请稍后再试");
            e.printStackTrace();
        }
        results.setData(result);
        return results;
    }



}
