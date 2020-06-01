package cn.bdqn.mapper;

import cn.bdqn.domain.Apply;

import java.util.List;

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

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    public List<Apply> selectByUserId(Integer userId);

}