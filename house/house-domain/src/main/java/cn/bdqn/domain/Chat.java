package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 聊天对象
 */
@Component
public class Chat {
    //聊天id
    private Integer id;

    //发送用户
    private User sendUser;

    //接收用户
    private User receptionUser;

    //发送消息
    private String message;

    //发送时间
    private Date sendTime;

    //聊天记录的状态 0正常 1删除
    private Integer state;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}