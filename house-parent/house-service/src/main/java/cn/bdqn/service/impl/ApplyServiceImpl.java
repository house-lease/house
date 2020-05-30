package cn.bdqn.service.impl;

import cn.bdqn.domain.Apply;
import cn.bdqn.domain.Money;
import cn.bdqn.mapper.ApplyMapper;
import cn.bdqn.mapper.MoneyMapper;
import cn.bdqn.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("applyServiceImpl")
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper mapper;

    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public boolean addInfo(Apply apply) {

        Money money = moneyMapper.selectByUerId(apply.getUser().getId());
        if (money.getMoney().compareTo(new BigDecimal(1000))==0||(money.getMoney().compareTo(new BigDecimal(1000))==1)){
            money.setMoney(money.getMoney().subtract(new BigDecimal(1000)));
//            修改用户可以资金
            moneyMapper.updateByUserId(money);
            mapper.insertInfo(apply);
            return true;
        }
        return false;

    }
}
