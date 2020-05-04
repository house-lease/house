package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户充值记录表
 */
@Component
public class Particular {

    //用户充值记录id
    private Integer id;

    //用户对象
    private User user;

    //用户姓名
    private String userName;

    //用户可以资金
    private BigDecimal money;

    //充值时间
    private Date refillTime;

    //记录状态 0正常 1删除
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getRefillTime() {
        return refillTime;
    }

    public void setRefillTime(Date refillTime) {
        this.refillTime = refillTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}