package cn.bdqn.service.impl;

import cn.bdqn.domain.Payment;
import cn.bdqn.mapper.PaymentMapper;
import cn.bdqn.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<Payment> queryByState() {
        return paymentMapper.selectByState();
    }
}
