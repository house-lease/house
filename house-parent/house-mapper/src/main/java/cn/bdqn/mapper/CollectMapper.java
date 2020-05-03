package cn.bdqn.mapper;

import cn.bdqn.domain.Collect;
import cn.bdqn.domain.User;

import java.util.List;

public interface CollectMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    /**
     * 根据id“删除”收藏信息
     */
    public int deleteInfoById(Integer id);

    /**
     * 根据用户id查询所有收藏信息
     */
    public List<Collect> selectAllInfoByUserId(Integer userId);

}