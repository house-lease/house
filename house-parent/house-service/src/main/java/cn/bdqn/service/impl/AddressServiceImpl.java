package cn.bdqn.service.impl;

import cn.bdqn.domain.Address;
import cn.bdqn.mapper.AddressMapper;
import cn.bdqn.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询父级地理位置
     * @return
     */
    @Override
    public List<Address> queryByParent() {
        return addressMapper.selectByParent();
    }


    /**
     * 根据父级id查询子级对象
     * @param parentId
     * @return
     */
    @Override
    public List<Address> queryByChild(Integer parentId) {
        return addressMapper.selectByChild(parentId);
    }
}
