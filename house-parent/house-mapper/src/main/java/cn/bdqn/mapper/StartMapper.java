package cn.bdqn.mapper;

import cn.bdqn.domain.Start;

import java.util.List;

public interface StartMapper {


    /**
     * 查询全部起租时间
     * @return
     */
    public List<Start> selectAll();


    int deleteByPrimaryKey(Integer id);

    int insert(Start record);

    int insertSelective(Start record);

    Start selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Start record);

    int updateByPrimaryKey(Start record);
}