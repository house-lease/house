package cn.bdqn.domain;

import org.springframework.stereotype.Component;

/**
 * 房屋详细信息对象
 */
@Component
public class HouseCareful {

    //房屋详细信息id
    private Integer id;

    //家具
    private String furniture;

    //房屋id
    private Integer houseId;

    //家具的状态 0有 1没有'
    private Integer state;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture == null ? null : furniture.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}