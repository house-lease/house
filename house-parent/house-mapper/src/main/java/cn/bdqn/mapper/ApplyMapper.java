package cn.bdqn.mapper;

import cn.bdqn.domain.Apply;

public interface ApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

    /**
     * 添加房东认证信息
     */
    public int insertInfo(Apply apply);

}