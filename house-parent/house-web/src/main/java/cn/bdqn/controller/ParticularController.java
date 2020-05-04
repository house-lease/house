package cn.bdqn.controller;

import cn.bdqn.domain.Particular;
import cn.bdqn.exception.MyException;
import cn.bdqn.service.ParticularService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public Result  save(Particular record) throws MyException {
        Result<Map<String, Object>> results = new Result<>();
        Map<String, Object> result = new HashMap<>();
        try {
            particularService.save(record);
            result.put("success", "充值成功");
        } catch (Exception e) {
            results.setMessage("充值失败去请稍后再试");
            e.printStackTrace();
            throw new MyException("充值失败");
        }
        results.setData(result);
        return results;
    }

    //删除充值记录
    public Result  delete(Integer id) throws MyException {
        Result<Map<String, Object>> results = new Result<>();
        Map<String, Object> result = new HashMap<>();
        try {
            particularService.deleteById(id);
            result.put("success", "删除成功");
        } catch (Exception e) {
            results.setMessage("删除失败去请稍后再试");
            e.printStackTrace();
            throw new MyException("删除失败");
        }
        results.setData(result);
        return results;
    }
}
