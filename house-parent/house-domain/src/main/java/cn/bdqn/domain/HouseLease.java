package cn.bdqn.domain;

import org.springframework.stereotype.Component;

/**
 * 房屋租赁类型对象
 */
@Component
public class HouseLease {

    //租赁类型id
    private Integer id;

    //租赁类型 整租 合租
    private String leaseType;

    //租赁类型的状态 0正常 1删除
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType == null ? null : leaseType.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}