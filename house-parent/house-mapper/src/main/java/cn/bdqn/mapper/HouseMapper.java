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
     * @param price  价格
     * @param startValue  起租时间实际数值
     * @return
     */
    List<House> selectByAddressORLeaseTypeORPriceORStartValue(@Param("address") String address, @Param("houseLeaseName") String houseLeaseName
            , @Param("price") BigDecimal price, @Param("startValue") Integer startValue);


    /**
     * 选择性更新
     * @param record
     */
    void updateByPrimaryKeySelective(House record);

    /**
     * 更新全部
     * @param record
     */
    void updateByPrimaryKey(House record);
}