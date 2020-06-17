package cn.bdqn.mapper;

import cn.bdqn.domain.House;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房屋接口
 */
public interface HouseMapper {

    /**
     * 添加房屋
     * @param record
     */
    void insert(House record);

    /**
     * 选择性添加房屋
     * @param record
     */
    void insertSelective(House record);

    /**
     * 根据房屋id查询房屋信息
     * @param id
     * @return
     */
    House selectByPrimaryKey(Integer id);

    /**
     * 根基
     * @param address 地区
     * @param houseLeaseName 房屋类型 整租 合租
     * @param startValue  起租时间实际数值
     * @return
     */
    List<House> selectByAddressORLeaseTypeORPriceORStartValue(@Param("address") String address,
                                                              @Param("houseLeaseName") String houseLeaseName
            , @Param("maxPrice") BigDecimal maxPrice, @Param("minPrice") BigDecimal minPrice,
                                                              @Param("startValue") Integer startValue,
                                                              @Param("houseTypeName") String houseTypeName);

    /**
     * 更新全部
     * @param record
     */
    void updateByPrimaryKey(House record);

    /**
     * 根据经纬度查询周边的房屋
     * @param latitude
     * @param longitude
     * @return
     */
    public List<House> selectRim(@Param("latitude") Double latitude,@Param("longitude") Double longitude,
                                 @Param("houseLeaseName") String houseLeaseName
            , @Param("maxPrice") BigDecimal maxPrice, @Param("minPrice") BigDecimal minPrice,
                                 @Param("startValue") Integer startValue);

    /**
     * 根据房东id查询
     * @param userId
     * @return
     */
    public List<House> selectByUserId(Integer userId);
}