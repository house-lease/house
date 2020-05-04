package cn.bdqn.domain;

import org.springframework.stereotype.Component;

/**
 * 城市对象
 */
@Component
public class Address {

    //城市id
    private Integer id;

    //城市名
    private String address;

    //父级id
    private Integer parentId;

    //城市的状态 0正常 1删除'
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}