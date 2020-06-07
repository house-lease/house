package cn.bdqn.controller;

import cn.bdqn.domain.Payment;
import cn.bdqn.domain.Record;
import cn.bdqn.exception.MyException;
import cn.bdqn.service.PaymentService;
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

    @Autowired
    private PaymentService paymentService;


    /**
     * 退还押金
     * @param id
     * @return
     */
    @RequestMapping("/returnMoney")
    @ResponseBody
    public Result returnMoney(Integer id) {
        Result result = new Result();
        try {
            Payment payment = recordService.returnMoney(id);
            result.setData(payment);
            result.setMessage("成功！");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(0);
            result.setMessage("失败");
            return result;
        }
    }

    /**
     * 提交缴费
     * @param id
     * @return
     */
    @RequestMapping("/pay")
    @ResponseBody
    public Result pay(Integer id){
        Result result = new Result();
        try {
            Payment payment = recordService.pay(id);
            result.setData(payment);
            result.setMessage("成功！");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(0);
            result.setMessage("失败");
            return result;
        }
    }

    /**
     * 添加订单以及还款对象
     * @param judge
     * @param payerUserId
     * @param payeeUserId
     * @param houseId
     * @param sumMoney
     * @param residueMoney
     * @param startValue
     * @return
     */
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
     * 更新订单
     * @param paymentId
     * @param sumMoney
     * @param residueMoney
     * @param startValue
     * @return
     */
    @RequestMapping("/updateRecord")
    @ResponseBody
    public Result updateRecord(Integer paymentId, BigDecimal sumMoney, BigDecimal residueMoney, Integer startValue){
        Result result = new Result();

        try {
            Integer fa = recordService.updateRecord(paymentId,sumMoney,residueMoney,startValue);
            result.setData(fa);
            result.setMessage("更新成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("失败");
            return result;
        }
    }

    /**
     * 根据付款用户id查询
     * @param payerUserId
     * @return
     */
    @RequestMapping("/queryByPayerUserId")
    @ResponseBody
    public Result queryByPayerUserId(Integer payerUserId,Integer dealState){

        Result result = new Result();
        try {
            List<Record> records = recordService.queryByPayerUserId(payerUserId,dealState);
            result.setData(records);
            result.setMessage("查询成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("失败");
            return result;
        }
    }

    /**
     * 根据收款用户id查询
     * @param payeeUserId
     * @return
     */
    @RequestMapping("/queryByPayeeUserId")
    @ResponseBody
    public Result queryByPayeeUserId(Integer payeeUserId,Integer dealState){

        Result result = new Result();
        try {
            List<Record> records = recordService.queryByPayeeUserId(payeeUserId,dealState);
            result.setData(records);
            result.setMessage("查询成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("失败");
            return result;
        }
    }


    /**
     * 更新订单状态
     * @param userId
     * @param id
     * @param dealState
     * @return
     */
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


    /**
     * 根据订单id查询付款对象
     * @param recordId
     * @return
     */
    @RequestMapping("/queryByRecordId")
    @ResponseBody
    public Result queryByRecordId(Integer recordId){
        Result result = new Result();
        try{
//            查询
            Payment payment = paymentService.queryByRecordId(recordId);
            result.setData(payment);
            result.setMessage("查询成功~");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("失败");
            return result;
        }
    }
}
