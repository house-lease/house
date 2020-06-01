package cn.bdqn.controller;

import cn.bdqn.domain.Record;
import cn.bdqn.exception.MyException;
import cn.bdqn.service.RecordService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理订单请求的controller
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping("/save")
    @ResponseBody
    public Result save(Record record) throws MyException {
        Result<Map<String, Object>> results = new Result<>();
        Map<String, Object> result = new HashMap<>();
        try {
            recordService.save(record);
            result.put("success", "付款成功");
        } catch (Exception e) {
            results.setMessage("付款失败去请稍后再试");
            e.printStackTrace();
            throw new MyException("付款失败");
        }
        results.setData(result);
        return results;
    }


    /**
     * 根据付款用户id查询
     * @param payerUserId
     * @return
     */
    @RequestMapping("/queryByPayerUserId")
    @ResponseBody
    public Result queryByPayerUserId(Integer payerUserId){

        Result result = new Result();
        try {
            List<Record> records = recordService.queryByPayerUserId(payerUserId);
            result.setData(records);
            result.setMessage("查询成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("失败");
            return result;
        }
    }


    @RequestMapping("/updateDealState")
    @ResponseBody
    public Result updateDealState(Integer userId,Integer id,Integer dealState){

        Result result = new Result();
        try {
//            修改状态
            List<Record> records = recordService.updateDealState(userId,id,dealState);
            result.setData(records);
            result.setMessage("修改成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("失败");
            return result;
        }
    }


}
