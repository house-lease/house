package cn.bdqn.service;

import cn.bdqn.domain.House;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房屋业务层
 */
public interface HouseService {


    /**
     * 根据经纬度查询周边的房屋
     * @param latitude
     * @param longitude
     * @return
     */
    public List<House> queryRim(Double latitude,Double longitude,String houseLeaseName,
                                BigDecimal maxPrice,
                                BigDecimal minPrice,
                                Integer startValue);


    /**
     * 添加房屋
     * @param record
     */
    public House save(House record);

    //查询房屋信息
    public List<House> queryByAddressORLeaseTypeORPriceORStartValue(String address,
                                                                    String houseLeaseName,
                                                                    BigDecimal maxPrice,
                                                                    BigDecimal minPrice,
                                                                    Integer startValue,
                                                                    String houseTypeName);


    /**
     * 根据房屋id查询房屋信息
     * @param id
     * @return
     */
    public House selectByPrimaryKey(Integer id);


}
