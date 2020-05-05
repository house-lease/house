package cn.bdqn.service.impl;

import cn.bdqn.domain.Collect;
import cn.bdqn.domain.House;
import cn.bdqn.domain.User;
import cn.bdqn.mapper.CollectMapper;
import cn.bdqn.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

    //自动注入mapper
    @Autowired
    private CollectMapper mapper;

    /**
     * 根据用户id和房屋id添加收藏
     */
    @Override
    public int insertCollectByUserIdAndHouseId(Integer userId, Integer houseId) {

        //创建一个user对象存入用户id
        User user = new User();
        user.setId(userId);

        //创建一个房屋对象存入房屋id
        House house = new House();
        house.setId(houseId);

        //创建收藏对象，为添加方法准备数据
        Collect collect = new Collect();
        //给收藏时间字段赋值
        collect.setCollectTime(new Date());
        //设置收藏状态
        collect.setState(0);
        //设置用户id
        collect.setUser(user);
        //设置房屋id
        collect.setHouse(house);

        ////调用mapper层的根据用户和房屋id添加记录的方法
        int num = mapper.insert(collect);

        return num;
    }

    /**
     * 根据收藏id删除收藏
     */
    @Override
    public int deleteCollectByCollectId(Integer CollectId) {

        //调用mapper层的根据收藏id“删除”记录的方法
        int num = mapper.deleteInfoById(CollectId);

        return num;
    }

    /**
     * 根据用户id查看收藏房屋信息
     */
    @Override
    public List<Collect> queryInfoByUser_id(Integer userId) {

        //调用mapper的根据用户id查询用户收藏记录的方法并返回该用户的全部收藏数据
        List<Collect> list = mapper.selectAllInfoByUserId(userId);

        return list;
    }
}
