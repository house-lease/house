package cn.bdqn.service;

import cn.bdqn.domain.HouseLease;
import cn.bdqn.domain.HouseType;

import java.util.List;

public interface HouseLeaseService {

    //    查询全部房屋租赁类型
    public List<HouseLease> queryAll();
}
