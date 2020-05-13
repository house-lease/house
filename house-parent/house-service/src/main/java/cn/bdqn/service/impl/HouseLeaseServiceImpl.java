package cn.bdqn.service.impl;

import cn.bdqn.domain.HouseLease;
import cn.bdqn.mapper.HouseLeaseMapper;
import cn.bdqn.service.HouseLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseLeaseServiceImpl implements HouseLeaseService {

    //自动注入
    @Autowired
    private HouseLeaseMapper houseLeaseMapper;

    //查询房屋租赁类型
    @Override
    public List<HouseLease> queryAll() {
        return houseLeaseMapper.selectAll();
    }
}
