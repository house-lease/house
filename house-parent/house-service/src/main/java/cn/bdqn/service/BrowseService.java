package cn.bdqn.service;

import cn.bdqn.domain.Browse;

import java.util.List;

/**
 * 浏览记录业务层
 */
public interface BrowseService {

    /**
     * 根据用户id和房屋id添加浏览记录
     */
    public void insertBrowseByUserIdAndHouseId(Integer userId, Integer houseId);


    /**
     * 根据收藏id删除浏览记录
     */
    public void deleteBrowseByBrowseId(Integer browseId);

    /**
     * 根据用户id查看浏览记录
     */
    public List<Browse> queryInfoByUser_id(Integer userId);

}
