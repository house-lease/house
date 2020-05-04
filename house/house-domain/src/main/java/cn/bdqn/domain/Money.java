package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 用户资金表
 */
@Component
public class Money {

    //资金id
    private Integer id;

    //用户
    private User user;

    //用户可以资金
    private BigDecimal money;

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", user=" + user +
                ", money=" + money +
                '}';
    }
}