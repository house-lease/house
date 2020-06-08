package cn.bdqn.service.impl;

import cn.bdqn.domain.Money;
import cn.bdqn.domain.Particular;
import cn.bdqn.domain.User;
import cn.bdqn.mapper.MoneyMapper;
import cn.bdqn.mapper.ParticularMapper;
import cn.bdqn.mapper.UserMapper;
import cn.bdqn.service.ParticularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service()
public class ParticularServiceImpl implements ParticularService {
    @Autowired
    private ParticularMapper particularMapper;

    @Autowired
    private MoneyMapper moneyMapper;

    @Autowired
    private UserMapper userMapper;

    //添加充值记录
    @Override
    public void save(Integer userId, BigDecimal money) {
        User user = userMapper.selectByPrimaryKey(userId);
        Money userMoney = moneyMapper.selectByUerId(userId);
        //充值金额
        userMoney.setMoney(userMoney.getMoney().add(money));
        //更新金额
        moneyMapper.updateByUserId(userMoney);

        //充值记录
        Particular particular = new Particular();
        particular.setMoney(money);
        particular.setRefillTime(new Date());
        particular.setState(0);
        particular.setUser(user);
        particular.setUserName(user.getUserName());
        //添加
        particularMapper.insert(particular);
    }

    //    根据id查询
    @Override
    public Particular queryByPrimaryKey(Integer id) {
        return particularMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户id更改充值记录状态
     * @param id
     */
    @Override
    public void updateById(Integer id) {

        particularMapper.updateById(id);
    }

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    @Override
    public List<Particular> queryByUserId(Integer userId) {
        return particularMapper.selectByUserId(userId);
    }

}
