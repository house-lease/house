package cn.bdqn.domain;

import java.util.Date;

public class chatTest {
    private Integer id;

    //发送用户
    private User sendUser;

    //接收用户
    private User receptionUser;

    private Integer receptionUserId;

    private String message;

    private Date sendTime;

    private Integer viewState;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getReceptionUserId() {
        return receptionUserId;
    }

    public void setReceptionUserId(Integer receptionUserId) {
        this.receptionUserId = receptionUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getViewState() {
        return viewState;
    }

    public void setViewState(Integer viewState) {
        this.viewState = viewState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getReceptionUser() {
        return receptionUser;
    }

    public void setReceptionUser(User receptionUser) {
        this.receptionUser = receptionUser;
    }
}