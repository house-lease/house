package cn.bdqn.service.impl;

import cn.bdqn.domain.Money;
import cn.bdqn.domain.Payment;
import cn.bdqn.mapper.MoneyMapper;
import cn.bdqn.mapper.PaymentMapper;
import cn.bdqn.service.PaymentService;
import cn.bdqn.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public List<Payment> queryByState() {
        return paymentMapper.selectByState();
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED)
    public void TimeOfPaymen() {
        List<Payment> payments = this.queryByState();
        payments.forEach(item->{
            int payerUserId = item.getPayerUser().getId();//付款人id
            int payeeUserId = item.getPayeeUser().getId();//收款人ID
            BigDecimal money = item.getHouse().getPrice().multiply(new BigDecimal(item.getHouse().getStartValue()));//所需支付的租金
            Money payerMoney = moneyMapper.selectByPrimaryKey(payerUserId);//付款人资金对象
            Money payeeMoney = moneyMapper.selectByPrimaryKey(payeeUserId);//收款人资金对象
            payerMoney.setMoney(payerMoney.getMoney().subtract(money));//付款
            payeeMoney.setMoney(payeeMoney.getMoney().add(money));//收款
            moneyMapper.updateByUserId(payeeMoney);//更新付款到数据库
            moneyMapper.updateByUserId(payerMoney);//更细收款
//            钱到位之后开始 修改房租日期
            Calendar calendar = Calendar.getInstance();//操作date的工具类
            calendar.setTime(item.getNextTime());//设置要操作的时间
            calendar.add(Calendar.MONTH, item.getHouse().getStartValue());//给要操作的时间加[item.getHouse().getStartValue()]个月,
            item.setStartTime(item.getNextTime());//设置上次的付款时间, 不出意外的话是房租到期时间
            item.setNextTime(calendar.getTime());//设置下次交租时间
            item.setDeliveryNumber(item.getDeliveryNumber()+1);//已交次数加1
            item.setSumMoney(money);//需支付的金额
            //并更新付款对象
            paymentMapper.updateByPrimaryKeySelective(item);
        });
    }


}
