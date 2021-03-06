package cn.bdqn.mapper;

import cn.bdqn.domain.Browse;
import cn.bdqn.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrowseMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Browse record);

    int insertSelective(Browse record);

    Browse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Browse record);

    int updateByPrimaryKey(Browse record);

    /**
     * 根据id“删除”收藏信息
     */
    public int deleteInfoById(Integer id);

    /**
     * 根据用户id查询所有收藏信息
     */
    public List<Browse> selectAllInfoByUserId(Integer userId);

    /**
     * 根据用户id和房屋id查询该用户是否收藏该房屋
     * @param userId
     * @param houseId
     * @return
     */
    public Browse selectByUserIdAndHouseId(@Param("userId") Integer userId, @Param("houseId") Integer houseId);

}