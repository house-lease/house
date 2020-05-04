package cn.bdqn.service;

/**
 * 浏览记录业务层
 */
public interface BrowseService {

    /**
     * 根据用户id和房屋id添加浏览记录
     */
    public int insertBrowseByUserIdAndHouseId(Integer userId, Integer houseId);


    /**
     * 根据收藏id删除浏览记录
     */
    public int deleteBrowseByBrowseId(Integer browseId);

    /**
     * 根据用户id查看浏览记录
     */
    public int queryInfoByUser_id(Integer userId);

}
