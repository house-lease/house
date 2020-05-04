package cn.bdqn.mapper;

import cn.bdqn.domain.Payment;

import java.util.List;

public interface PaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);

    List<Payment> selectByState();
}