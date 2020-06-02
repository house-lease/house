package cn.bdqn.service;

import cn.bdqn.domain.Record;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单业务层
 */
public interface RecordService {
    /**
     * 保存订单
     * @param
     */
    public Integer save(Integer judge,Integer payerUserId, Integer payeeUserId, Integer houseId, BigDecimal sumMoney, BigDecimal residueMoney,
              Integer startValue);


    //    根据付款用户id查询
    public List<Record> queryByPayerUserId(Integer payerUserId);

//    根据订单id修改订单支付状态
    public List<Record> updateDealState(Integer userId,Integer id,Integer dealState);
}
