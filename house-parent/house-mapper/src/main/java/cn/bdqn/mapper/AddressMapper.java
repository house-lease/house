package cn.bdqn.mapper;

import cn.bdqn.domain.Address;

import java.awt.*;
import java.util.List;

public interface AddressMapper {

    void deleteByPrimaryKey(Integer id);

    void insert(Address record);

    void insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(Address record);

    void updateByPrimaryKey(Address record);


    // 查询父级地理位置
    public List<Address> selectByParent();
}