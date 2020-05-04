package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 付款对象
 */
@Component
public class Payment {

    //付款id
    private Integer id;

    //付款人对象
    private User payerUser;

    //付款人姓名
    private String payerName;

    //收款人对象
    private User payeeUser;

    //收款人姓名
    private String payeeName;

    //房屋对象
    private House house;

    //房屋名字
    private String houseName;

    //押金状态  0 代表已交 1 代表已退
    private Integer cashState;

    //需支付的总金额
    private BigDecimal sumMoney;

    //剩余的金额
    private BigDecimal residueMoney;

    //总次数
    private Integer number;

    //已交次数
    private Integer deliveryNumber;

    //首次交付时间
    private Date startTime;

    //下次交付时间
    private Date nextTime;

    //付款状态  0 代表已完成  1 代表还款中
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }



    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public User getPayerUser() {
        return payerUser;
    }

    public void setPayerUser(User payerUser) {
        this.payerUser = payerUser;
    }

    public User getPayeeUser() {
        return payeeUser;
    }

    public void setPayeeUser(User payeeUser) {
        this.payeeUser = payeeUser;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public Integer getCashState() {
        return cashState;
    }

    public void setCashState(Integer cashState) {
        this.cashState = cashState;
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public BigDecimal getResidueMoney() {
        return residueMoney;
    }

    public void setResidueMoney(BigDecimal residueMoney) {
        this.residueMoney = residueMoney;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(Integer deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", payerUser=" + payerUser +
                ", payerName='" + payerName + '\'' +
                ", payeeUser=" + payeeUser +
                ", payeeName='" + payeeName + '\'' +
                ", house=" + house +
                ", houseName='" + houseName + '\'' +
                ", cashState=" + cashState +
                ", sumMoney=" + sumMoney +
                ", residueMoney=" + residueMoney +
                ", number=" + number +
                ", deliveryNumber=" + deliveryNumber +
                ", startTime=" + startTime +
                ", nextTime=" + nextTime +
                ", state=" + state +
                '}';
    }
}