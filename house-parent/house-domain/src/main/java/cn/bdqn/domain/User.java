package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户表
 */
@Component
public class User {
    //用户id
    private Integer id;

    //用户昵称
    private String nickname;

    //用户性别
    private Integer sex;

    //用户名
    private String userName;

    //身份证号
    private String idcard;

    //手机号
    private String phone;

    //房东认证 0代表普通用户 1代表房东
    private Integer landlord;

    //注册时间
    private Date registerTime;

    //头像路径
    private String imageUrl;

    //用户的唯一标识
    private String openId;


    //用户状态 0 代表正常 1 代表失效
    private Integer state;

    public Integer getId() {
        return id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getLandlord() {
        return landlord;
    }

    public void setLandlord(Integer landlord) {
        this.landlord = landlord;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", userName='" + userName + '\'' +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", landlord=" + landlord +
                ", registerTime=" + registerTime +
                ", imageUrl='" + imageUrl + '\'' +
                ", openId='" + openId + '\'' +
                ", state=" + state +
                '}';
    }
}