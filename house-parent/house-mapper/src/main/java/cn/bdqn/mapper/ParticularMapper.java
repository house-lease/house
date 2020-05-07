package cn.bdqn.mapper;

import cn.bdqn.domain.Particular;

import java.util.List;

public interface ParticularMapper {


    //新增
    void insert(Particular record);


    //根据id查询
    Particular selectByPrimaryKey(Integer id);


//    根据id删除
    void updateById(Integer id);

//    根据用户id查询
    public List<Particular> selectByUserId(Integer userId);
}