package cn.bdqn.mapper;

import cn.bdqn.domain.HouseType;

import java.util.List;

public interface HouseTypeMapper {


//    查询全部房屋类型
    public List<HouseType> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(HouseType record);

    int insertSelective(HouseType record);

    HouseType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseType record);

    int updateByPrimaryKey(HouseType record);
}