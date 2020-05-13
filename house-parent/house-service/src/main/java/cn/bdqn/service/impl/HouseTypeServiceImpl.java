package cn.bdqn.service.impl;

import cn.bdqn.domain.HouseType;
import cn.bdqn.mapper.HouseTypeMapper;
import cn.bdqn.service.HouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseTypeServiceImpl implements HouseTypeService {


    @Autowired
    private HouseTypeMapper houseTypeMapper;
    //查询房屋类型
    @Override
    public List<HouseType> queryAll() {
        return houseTypeMapper.selectAll();
    }
}
