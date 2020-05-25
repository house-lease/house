package cn.bdqn.domain;

import java.util.Date;

public class ChatTest {
    private Integer id;

    private Integer isMyYou;
    //发送用户
    private User sendUser;

    //接收用户
    private User receptionUser;

    private String message;

    private Date sendTime;

    private String sendTimeString;

    private Integer viewState;

    private Integer state;

    public String getSendTimeString() {
        return sendTimeString;
    }

    public void setSendTimeString(String sendTimeString) {
        this.sendTimeString = sendTimeString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getIsMyYou() {
        return isMyYou;
    }

    public void setIsMyYou(Integer isMyYou) {
        this.isMyYou = isMyYou;
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


    @Override
    public String toString() {
        return "ChatTest{" +
                "id=" + id +
                ", isMyYou=" + isMyYou +
                ", sendUser=" + sendUser +
                ", receptionUser=" + receptionUser +
                ", message='" + message + '\'' +
                ", sendTime=" + sendTime +
                ", sendTimeString='" + sendTimeString + '\'' +
                ", viewState=" + viewState +
                ", state=" + state +
                '}';
    }
}