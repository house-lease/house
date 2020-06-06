package cn.bdqn.domain;

import java.util.Date;

//租客信息表
public class Tenant {
    //id
    private Integer id;

    //租客
    private User user;

    //租的房子
    private Integer houseId;

    //房屋到期时间
    private Date terminationTime;

    //状态 0 在住 1搬离
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


    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(Date terminationTime) {
        this.terminationTime = terminationTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", user=" + user +
                ", houseId=" + houseId +
                ", terminationTime=" + terminationTime +
                ", state=" + state +
                '}';
    }
}