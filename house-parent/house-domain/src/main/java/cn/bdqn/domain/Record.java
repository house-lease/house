package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对象
 */
@Component
public class Record {

    //订单id
    private Integer id;

    //订单号
    private String record;

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

    //交易时间
    private Date dealTime;

    //交易金额
    private BigDecimal dealMoney;

    //交易状态 0 完成 1 未支付  2 取消
    private Integer dealState;

    //订单状态 0正常 1删除
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getPayerName() {

        return payerName == null ? this.payerUser.getUserName() : null;
    }

    public void setPayerName(String payerName) {

        this.payerName = payerName == null ? null : payerName.trim();
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

    public String getPayeeName()
    {
        return payeeName == null ? this.payeeUser.getUserName() : null;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }



    public String getHouseName() {

        return houseName == null ? this.getHouse().getHouseName() : null;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public BigDecimal getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }

    public Integer getDealState() {
        return dealState;
    }

    public void setDealState(Integer dealState) {
        this.dealState = dealState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", record='" + record + '\'' +
                ", payerUser=" + payerUser +
                ", payerName='" + payerName + '\'' +
                ", payeeUser=" + payeeUser +
                ", payeeName='" + payeeName + '\'' +
                ", house=" + house +
                ", houseName='" + houseName + '\'' +
                ", dealTime=" + dealTime +
                ", dealMoney=" + dealMoney +
                ", dealState=" + dealState +
                ", state=" + state +
                '}';
    }
}