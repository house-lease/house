package cn.bdqn.mapper;

import cn.bdqn.domain.Address;

public interface AddressMapper {

    void deleteByPrimaryKey(Integer id);

    void insert(Address record);

    void insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(Address record);

    void updateByPrimaryKey(Address record);
}