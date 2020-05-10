package cn.bdqn.service;

import cn.bdqn.domain.Address;

import java.util.List;

//地理位置业务接口
public interface AddressService {

    public List<Address> queryByParent();
}
