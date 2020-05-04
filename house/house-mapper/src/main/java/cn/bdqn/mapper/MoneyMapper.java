package cn.bdqn.mapper;

import cn.bdqn.domain.Money;


/**
 * 用户可用资金
 */
public interface MoneyMapper {
    int deleteByPrimaryKey(Integer id);

//    添加用户用资金
    int insert(Money record);

    int insertSelective(Money record);

//    根据id查询
    Money selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Money record);

//    根据用户id修改钱数
    int updateByUserId(Money record);

//    根据用户id查询用户可用余额
    public Money selectByUerId(Integer userId);

}