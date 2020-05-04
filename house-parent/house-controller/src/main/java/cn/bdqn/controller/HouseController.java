package cn.bdqn.controller;

import cn.bdqn.domain.House;
import cn.bdqn.domain.HouseImage;
import cn.bdqn.service.HouseService;
import cn.bdqn.utils.Result;
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


    /**
     * 查询房屋方法

     * @return
     */
    @RequestMapping("/queryHouse")
    @ResponseBody
    public Result queryHouse(String address, String houseLeaseName, BigDecimal price, Integer startValue){

        List<House> houses = new ArrayList<>();
        try {
            //调用查询房屋的方法
            houses = houseService.queryByAddressORLeaseTypeORPriceORStartValue(address,"".equals(houseLeaseName.trim())?null:houseLeaseName,price,startValue);
            Result result = new Result();
            result.setData(houses);
            result.setMessage("加载完成");
            return result;
        }catch (Exception e){
            Result result = new Result();
            e.printStackTrace();
            result.setData(null);
            result.setMessage("网络异常");
            return result;
        }

    }

    @RequestMapping("/queryByHouseId")
    @ResponseBody
    public Result queryByHouseId(Integer houseId){
        try{
            Result result = new Result();
            //根据id查询房屋详细信息
            House house = houseService.selectByPrimaryKey(houseId);
            result.setData(house);
            result.setMessage("加载完成");
            return result;
        }catch (Exception e){
            Result result = new Result();
            e.printStackTrace();
            result.setData(null);
            result.setMessage("网络异常");
            return result;
        }
    }
}
