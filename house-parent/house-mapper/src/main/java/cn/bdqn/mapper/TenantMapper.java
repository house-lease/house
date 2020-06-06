package cn.bdqn.mapper;

import cn.bdqn.domain.Tenant;

import java.util.List;

public interface TenantMapper {

//    根据房屋id查询租客信息
    public List<Tenant> selectByHouseId(Integer houseId);

    //查询全部
    public List<Tenant> selectAll();

    int deleteByPrimaryKey(Integer id);

//    新增租客信息
    int insert(Tenant record);

    int insertSelective(Tenant record);

    Tenant selectByPrimaryKey(Integer id);

//    更新
    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);
}