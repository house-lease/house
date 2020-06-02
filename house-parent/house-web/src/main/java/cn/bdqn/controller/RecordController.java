package cn.bdqn.controller;

import cn.bdqn.domain.Record;
import cn.bdqn.exception.MyException;
import cn.bdqn.service.RecordService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
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
    public Result save(Integer judge, Integer payerUserId,
                       Integer payeeUserId, Integer houseId,
                       BigDecimal sumMoney, BigDecimal residueMoney,
                       Integer startValue) {
        Result results = new Result<>();
        try {
            //新增订单
            Integer fa = recordService.save(judge,payerUserId,payeeUserId,houseId,sumMoney,residueMoney,startValue);
            results.setData(fa);
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return results;
        }
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
