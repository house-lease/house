package cn.bdqn.domain;

import org.springframework.stereotype.Component;

/**
 * 房屋图片对象
 */
@Component
public class HouseImage {
    //房屋图片id
    private Integer id;

    //房屋图片位置对象
    private ImagePlace imagePlace;

    //房屋id
    private Integer houseId;

    //图片路径
    private String imageUrl;

    //图片的状态 0正常 1删除
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImagePlace getImagePlace() {
        return imagePlace;
    }

    public void setImagePlace(ImagePlace imagePlace) {
        this.imagePlace = imagePlace;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
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
}