package cn.bdqn.controller;

import cn.bdqn.domain.*;
import cn.bdqn.service.HouseService;
import cn.bdqn.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {

    //房屋业务类
    @Autowired
    private HouseService houseService;



    @RequestMapping("/rim")
    @ResponseBody
    public Result queryRim(Double latitude, Double longitude,String houseLeaseName,
                           BigDecimal maxPrice,
                           BigDecimal minPrice,
                           Integer startValue){
        Result result = new Result();
        try {
            List<House> houses = houseService.queryRim(latitude,longitude,"".equals(houseLeaseName.trim())?null:houseLeaseName,maxPrice,minPrice,startValue);
            result.setData(houses);
            result.setMessage("查询成功~");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询失败~");
            return result;
        }
    }

    /**
     * 添加房屋的方法
     * @param user
     * @param address
     * @param latitude
     * @param longitude
     * @param start
     * @param price
     * @param narrate
     * @param houseName
     * @param uptown
     * @param houseType
     * @param houseLease
     * @param houseCareful
     * @param residueRoom
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(String user, String address, Double latitude, Double longitude, String start, BigDecimal price,
                       String narrate, String houseName, String uptown, String houseType, String houseLease,
                       String houseCareful, Integer residueRoom){

        Start start1 =  JSONObject.parseObject(start,Start.class);
        User user1 =  JSONObject.parseObject(user,User.class);
        HouseCareful houseCareful1 =  JSONObject.parseObject(houseCareful,HouseCareful.class);
        HouseType houseType1 =  JSONObject.parseObject(houseType,HouseType.class);
        HouseLease houseLease1 =  JSONObject.parseObject(houseLease,HouseLease.class);
        Result result = new Result();
        try {
            House house = new House();
            house.setAddress(address);
            house.setHouseCareful(houseCareful1);
            house.setHouseLease(houseLease1);
            house.setHouseLeaseName(houseLease1.getLeaseType());
            house.setHouseName(houseName);
            house.setUser(user1);
            house.setUserName(user1.getUserName());
            house.setLatitude(latitude);
            house.setLongitude(longitude);
            house.setPrice(price);
            house.setResidueRoom(residueRoom);
            house.setUptown(uptown);
            house.setNarrate(narrate);
            house.setStart(start1);
            house.setStartName(start1.getStartName());
            house.setStartValue(start1.getStartValue());
            house.setHouseType(houseType1);
            house.setHouseTypeName(houseType1.getHouseType());
            house.setState(0);
            House house1 = houseService.save(house);
            result.setData(house1);
            result.setMessage("添加成功~");
            return result;
        }catch (Exception e){
            result.setMessage("添加失败~");
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 查询房屋方法

     * @return
     */
    @RequestMapping("/queryHouse")
    @ResponseBody
    public Result queryHouse(String address, String houseLeaseName, BigDecimal maxPrice,BigDecimal minPrice, Integer startValue,String houseTypeName){

        List<House> houses = new ArrayList<>();
        Result result = new Result();
        try {
            //调用查询房屋的方法
            houses = houseService.queryByAddressORLeaseTypeORPriceORStartValue(address,"".equals(houseLeaseName.trim())?null:houseLeaseName,
                    maxPrice,minPrice,startValue,"".equals(houseTypeName.trim())?null:houseTypeName);

            result.setData(houses);
            result.setMessage("加载完成");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(null);
            result.setMessage("网络异常");
            return result;
        }

    }

    @RequestMapping("/queryByHouseId")
    @ResponseBody
    public Result queryByHouseId(Integer houseId){
        Result result = new Result();
        try{

            //根据id查询房屋详细信息
            House house = houseService.selectByPrimaryKey(houseId);
            result.setData(house);
            result.setMessage("加载完成");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(null);
            result.setMessage("网络异常");
            return result;
        }
    }
}
