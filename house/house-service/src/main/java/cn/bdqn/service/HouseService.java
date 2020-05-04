package cn.bdqn.service;

import cn.bdqn.domain.House;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房屋业务层
 */
public interface HouseService {

    //查询房屋信息
    public List<House> queryByAddressORLeaseTypeORPriceORStartValue(String address, String houseLeaseName, BigDecimal price, Integer startValue);


    /**
     * 根据房屋id查询房屋信息
     * @param id
     * @return
     */
    public House selectByPrimaryKey(Integer id);


}
