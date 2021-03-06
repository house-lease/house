package cn.bdqn.domain;

import java.util.Date;

public class ChatList {
    private Integer id;

    //发送用户
    private User sendUser;

    //接收用户
    private User receptionUser;


    private String message;

    private Date sendTime;

    private String sendTimeString;

    private Integer unread;

    private Integer state;

//    最后发送用户的id
    private Integer theSendUserId;

    public Integer getTheSendUserId() {
        return theSendUserId;
    }

    public void setTheSendUserId(Integer theSendUserId) {
        this.theSendUserId = theSendUserId;
    }

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

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}