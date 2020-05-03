package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 收藏对象
 */
@Component
public class Collect {
    //收藏id
    private Integer id;

    //收藏用户对象
    private User user;

    //收藏房屋id
    private House house;

    //收藏时间
    private Date collectTime;

    //收藏状态 0正常 1删除
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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}