package cn.bdqn.domain;

import org.springframework.stereotype.Component;

/***
 * 图片位置对象
 */
@Component
public class ImagePlace {

    //图片位置id
    private Integer id;

    //图片位置
    private String place;

    //图片位置的状态 0正常 1删除
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}