package cn.bdqn.mapper;

import cn.bdqn.domain.Particular;

public interface ParticularMapper {
    int deleteByPrimaryKey(Integer id);
   //新增充值记录
    int insert(Particular record);

    int insertSelective(Particular record);

    Particular selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Particular record);

    int updateByPrimaryKey(Particular record);
     //删除充值记录
     int  updateById(Integer id);
}