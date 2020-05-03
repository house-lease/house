package cn.bdqn.domain;

import org.springframework.stereotype.Component;

/**
 * 房屋类型对象
 */
@Component
public class HouseType {
    //房屋类型id
    private Integer id;

    //房屋类型 如 三室一厅
    private String houseType;

    //房屋类型的状态 0正常 1删除'
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}