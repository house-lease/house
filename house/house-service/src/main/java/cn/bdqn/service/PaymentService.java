package cn.bdqn.service;

import cn.bdqn.domain.Payment;

import java.util.List;

public interface PaymentService {

    /**
     * 查询该交钱的记录, 定时付款
     * @return
     */
    List<Payment> queryByState();

    /**
     * 定时付款
     */
    void TimeOfPaymen();
}
