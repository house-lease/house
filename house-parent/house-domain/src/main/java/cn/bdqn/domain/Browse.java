package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

/***
 * 浏览记录对象
 */
@Component
public class Browse {

    //浏览记录id
    private Integer id;

    //浏览人对象
    private User user;

    //房屋对象
    private House house;

    //浏览时间
    private Date browseTime;

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

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}