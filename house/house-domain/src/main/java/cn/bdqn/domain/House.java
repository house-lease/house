package cn.bdqn.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 房屋对象
 */
@Component
public class House {

    //房屋id
    private Integer id;

    //房东对象
    private User user;

    //房东姓名
    private String userName;

    //纬度
    private Double latitude;

    //经度
    private Double longitude;

    //起租时间对象
    private Start start;

    //起租时间
    private String startName;

    //起租实际类型
    private Integer startValue;

    //月价格
    private BigDecimal price;

    //房屋描述
    private String narrate;

    //房屋名字
    private String houseName;

    //小区
    private String uptown;

    //城市
    private String address;

    //房屋类型对象
    private HouseType houseType;

    //房屋类型名字
    private String houseTypeName;

    //房屋租赁类型
    private HouseLease houseLease;

    //房屋租赁类型名字
    private String houseLeaseName;

    //房屋图片列表
    public List<HouseImage> houseImages  = new ArrayList<>();

    //房屋详细信息数组
    public List<HouseCareful> houseCareful = new ArrayList<>();

    //剩余房间
    private Integer residueRoom;

    //房屋状态 0可租 1满租
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HouseCareful> getHouseCareful() {
        return houseCareful;
    }

    public void setHouseCareful(List<HouseCareful> houseCareful) {
        this.houseCareful = houseCareful;
    }

    public Integer getStartValue() {
        return startValue;
    }

    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName == null ? null : startName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate == null ? null : narrate.trim();
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public String getUptown() {
        return uptown;
    }

    public void setUptown(String uptown) {
        this.uptown = uptown == null ? null : uptown.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }



    public String getHouseTypeName() {
        return houseTypeName;
    }



    public List<HouseImage> getHouseImages() {
        return houseImages;
    }

    public void setHouseImages(List<HouseImage> houseImages) {
        this.houseImages = houseImages;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName == null ? null : houseTypeName.trim();
    }



    public String getHouseLeaseName() {
        return houseLeaseName;
    }

    public void setHouseLeaseName(String houseLeaseName) {
        this.houseLeaseName = houseLeaseName == null ? null : houseLeaseName.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public HouseLease getHouseLease() {
        return houseLease;
    }

    public void setHouseLease(HouseLease houseLease) {
        this.houseLease = houseLease;
    }


    public Integer getResidueRoom() {
        return residueRoom;
    }

    public void setResidueRoom(Integer residueRoom) {
        this.residueRoom = residueRoom;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", user=" + user +
                ", userName='" + userName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", start=" + start +
                ", startName='" + startName + '\'' +
                ", startValue=" + startValue +
                ", price=" + price +
                ", narrate='" + narrate + '\'' +
                ", houseName='" + houseName + '\'' +
                ", uptown='" + uptown + '\'' +
                ", address='" + address + '\'' +
                ", houseType=" + houseType +
                ", houseTypeName='" + houseTypeName + '\'' +
                ", houseLease=" + houseLease +
                ", houseLeaseName='" + houseLeaseName + '\'' +
                ", houseImages=" + houseImages +
                ", houseCareful=" + houseCareful +
                ", residueRoom=" + residueRoom +
                ", state=" + state +
                '}';
    }
}