package cn.bdqn.service.impl;

import cn.bdqn.domain.*;
import cn.bdqn.mapper.*;
import cn.bdqn.service.RecordService;
import cn.bdqn.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private MoneyMapper moneyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public Integer save(Integer judge,Integer payerUserId, Integer payeeUserId, Integer houseId, BigDecimal sumMoney,BigDecimal residueMoney,
                     Integer startValue) {

//        付款人对象
        User payerUser = userMapper.selectByPrimaryKey(payerUserId);
        //收款人对象
        User payeeUser = userMapper.selectByPrimaryKey(payeeUserId);
        //房屋对象
        House house = houseMapper.selectByPrimaryKey(houseId);
//        获得付款人的账户余额
        Money payerUserMoney = moneyMapper.selectByUerId(payerUserId);
//         获得收款人的账户余额
        Money payeeUserMoney = moneyMapper.selectByUerId(payeeUserId);
        // 订单对象
        Record record = new Record();
        //            还款对象
        Payment payment = new Payment();
        if (house.getResidueRoom()>0){
            if (judge==0){
                if (payerUserMoney.getMoney().compareTo(house.getPrice().add(new BigDecimal(1000)))==0||(payerUserMoney.getMoney().compareTo(house.getPrice().add(new BigDecimal(1000)))==1)){
//          扣除首付金额
                    payerUserMoney.setMoney(payerUserMoney.getMoney().subtract(house.getPrice().add(new BigDecimal(1000))));
                    moneyMapper.updateByUserId(payerUserMoney);
                    //增加首付金额
                    payeeUserMoney.setMoney(payeeUserMoney.getMoney().add(house.getPrice().add(new BigDecimal(1000))));
                    moneyMapper.updateByUserId(payeeUserMoney);
//           订单对象
                    record.setPayeeUser(payeeUser);
                    record.setPayeeName(payeeUser.getUserName());
                    record.setPayerUser(payerUser);
                    record.setPayerName(payerUser.getUserName());
                    record.setDealMoney(sumMoney);
                    record.setHouse(house);
                    record.setHouseName(house.getHouseName());
                    record.setDealTime(new Date());
                    record.setState(0);
                    record.setDealState(1);
                    record.setRecord(DateUtil.date2String1(new Date()));
                    recordMapper.insert(record);

//            还款对象

                    payment.setPayeeUser(payeeUser);
                    payment.setPayeeName(payeeUser.getUserName());
                    payment.setPayerUser(payerUser);
                    payment.setPayerName(payerUser.getUserName());
                    payment.setHouse(house);
                    payment.setHouseName(house.getHouseName());
                    payment.setStartTime(new Date());
//            测试用要修改
                    payment.setNextTime(DateUtil.string2Date(new Date(),30));
                    payment.setDeliveryNumber(1);
                    payment.setSumMoney(sumMoney);
                    payment.setCashState(0);
                    payment.setNumber(startValue);
                    payment.setRecordId(record.getId());
                    payment.setState(1);
                    payment.setResidueMoney(residueMoney);

                    if ((payment.getNumber()-payment.getDeliveryNumber())==0){
                        payment.setNextTime(null);
                        payment.setState(0);
                        record.setDealState(0);
                        recordMapper.updateByPrimaryKeySelective(record);
                    }
                    paymentMapper.insert(payment);

//                   更新房屋可租房间数
                    house.setResidueRoom(house.getResidueRoom()-1);
                    if (house.getResidueRoom()<=0){
                        house.setState(1);
                    }
//                更新房屋状态
                    houseMapper.updateByPrimaryKey(house);
//                    0代表交易成功
                    return 0;
                }else {
//                    1代表余额不足
                    return 1;
                }
            }else {
                //订单对象封装
                record.setPayeeUser(payeeUser);
                record.setPayeeName(payeeUser.getUserName());
                record.setPayerUser(payerUser);
                record.setPayerName(payerUser.getUserName());
                record.setDealMoney(sumMoney);
                record.setHouse(house);
                record.setHouseName(house.getHouseName());
                record.setDealTime(new Date());
                record.setState(0);
                record.setDealState(3);
                record.setRecord(DateUtil.date2String1(new Date()));
                recordMapper.insert(record);
                //还款对象封装
                payment.setPayeeUser(payeeUser);
                payment.setPayeeName(payeeUser.getUserName());
                payment.setPayerUser(payerUser);
                payment.setPayerName(payerUser.getUserName());
                payment.setHouse(house);
                payment.setHouseName(house.getHouseName());
                payment.setCashState(2);
                payment.setNumber(startValue);
                payment.setRecordId(record.getId());
                payment.setState(2);
                paymentMapper.insert(payment);
//                2取消交易存在在订单中
                return 2;
            }

        }else {
//            3没有可租房间
            return 3;
        }

    }

    /**
     * 修改订单
     * @param paymentId
     * @param sumMoney
     * @param residueMoney
     * @param startValue
     * @return
     */
    @Override
    public Integer updateRecord(Integer paymentId, BigDecimal sumMoney, BigDecimal residueMoney, Integer startValue) {

        Payment payment = paymentMapper.selectByPrimaryKey(paymentId);
        Record record = recordMapper.selectByPrimaryKey(payment.getRecordId());

        //        获得付款人的账户余额
        Money payerUserMoney = moneyMapper.selectByUerId(payment.getPayerUser().getId());
//         获得收款人的账户余额
        Money payeeUserMoney = moneyMapper.selectByUerId(payment.getPayeeUser().getId());

        if (payment.getHouse().getResidueRoom()>0){
            if (payerUserMoney.getMoney().compareTo(payment.getHouse().getPrice().add(new BigDecimal(1000)))==0||(payerUserMoney.getMoney().compareTo(payment.getHouse().getPrice().add(new BigDecimal(1000)))==1)){
//          扣除首付金额
                payerUserMoney.setMoney(payerUserMoney.getMoney().subtract(payment.getHouse().getPrice().add(new BigDecimal(1000))));
                moneyMapper.updateByUserId(payerUserMoney);
                //增加首付金额
                payeeUserMoney.setMoney(payeeUserMoney.getMoney().add(payment.getHouse().getPrice().add(new BigDecimal(1000))));
                moneyMapper.updateByUserId(payeeUserMoney);
//                更新订单状态
                record.setDealState(1);
                record.setDealMoney(sumMoney);
                //更新还款表
                payment.setResidueMoney(residueMoney);
                payment.setSumMoney(sumMoney);
                payment.setCashState(0);
                payment.setNumber(startValue);
                payment.setDeliveryNumber(1);
                payment.setStartTime(new Date());
                payment.setNextTime(DateUtil.string2Date(new Date(),30));
                payment.setState(1);
                if ((payment.getNumber()-payment.getDeliveryNumber())==0){
                    payment.setNextTime(null);
                    payment.setState(0);
                    record.setDealState(0);
                }

//                更新订单和付款
                recordMapper.updateByPrimaryKeySelective(record);
                paymentMapper.updateByPrimaryKeySelective(payment);

                //                   更新房屋可租房间数
                payment.getHouse().setResidueRoom(payment.getHouse().getResidueRoom()-1);
                if (payment.getHouse().getResidueRoom()<=0){
                    payment.getHouse().setState(1);
                }
//                更新房屋状态
                houseMapper.updateByPrimaryKey(payment.getHouse());
                //交易成功
                return 0;
            }else {
//                    1代表余额不足
                return 1;
            }


        }else {
//            3没有可租房间
            return 2;
        }

    }

    //    根据付款用户id查询
    @Override
    public List<Record> queryByPayerUserId(Integer payerUserId) {
        return recordMapper.selectByPayerUserId(payerUserId);
    }

//    根据订单id修改订单支付状态
    @Override
    public List<Record> updateDealState(Integer userId,Integer id, Integer dealState) {
//        修改订单状态
        recordMapper.updateDealState(id,dealState);
//        查询最新订单列表
        return recordMapper.selectByPayerUserId(userId);
    }
}
