package cn.bdqn.service.impl;

import cn.bdqn.domain.House;
import cn.bdqn.domain.HouseCareful;
import cn.bdqn.domain.HouseImage;
import cn.bdqn.mapper.HouseCarefulMapper;
import cn.bdqn.mapper.HouseImageMapper;
import cn.bdqn.mapper.HouseMapper;
import cn.bdqn.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("houseService")
public class HouseServiceImpl implements HouseService {

    //注入房屋接口
    @Autowired
    private HouseMapper houseMapper;

    //房屋图片接口
    @Autowired
    private HouseImageMapper houseImageMapper;

    @Autowired
    private HouseCarefulMapper houseCarefulMapper;

    /**
     * 根据经纬度查询周边的房屋
     * @param latitude
     * @param longitude
     * @return
     */
    @Override
    public List<House> queryRim(Double latitude, Double longitude) {
        //根据经纬度查询周边房屋
        List<House> houses = houseMapper.selectRim(latitude,longitude);
        //根据房屋id循环查找房屋图片
        for (House house:houses) {
            //调用根据房屋id查询房屋图片的方法
            List<HouseImage> images = houseImageMapper.selectByHouseId(house.getId());
            //添加图片
            house.setHouseImages(images);
        }
        return houses;
    }

    //    添加房屋的方法
    @Override
    public House save(House record) {

        //调用添加方法
        houseMapper.insert(record);

        //设置房屋id
        record.getHouseCareful().setHouseId(record.getId());
        //添加房屋详细信息
        houseCarefulMapper.insert(record.getHouseCareful());

        return record;

    }

    /**
     * 查询房屋信息
     */
    @Override
    public List<House> queryByAddressORLeaseTypeORPriceORStartValue(String address,String houseLeaseName,BigDecimal price,Integer startValue) {
        //查询房屋信息列表
        List<House> houses = houseMapper.selectByAddressORLeaseTypeORPriceORStartValue(address,houseLeaseName,price,startValue);
        //根据房屋id循环查找房屋图片
        for (House house:houses) {
            //调用根据房屋id查询房屋图片的方法
            List<HouseImage> images = houseImageMapper.selectByHouseId(house.getId());
            //添加图片
            house.setHouseImages(images);
        }
        return houses;
    }
    /**
     * 根据房屋id查询
     * @param id
     * @return
     */
    @Override
    public House selectByPrimaryKey(Integer id) {
        //根据id查询房屋信息
        House house = houseMapper.selectByPrimaryKey(id);
        //根据房屋id查询房屋图片信息
        List<HouseImage> houseImages = houseImageMapper.selectByHouseId(id);
        house.setHouseImages(houseImages);
        //返回对象
        return house;
    }
}
