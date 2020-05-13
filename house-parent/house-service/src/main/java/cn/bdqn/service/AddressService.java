package cn.bdqn.service;

import cn.bdqn.domain.Address;

import java.util.List;

//地理位置业务接口
public interface AddressService {


    /**
     * 查询父级地理位置对象
     * @return
     */
    public List<Address> queryByParent();

    /**
     * 根据父级id查询子级对象
     * @param parentId
     * @return
     */
    public List<Address> queryByChild(Integer parentId);
}
