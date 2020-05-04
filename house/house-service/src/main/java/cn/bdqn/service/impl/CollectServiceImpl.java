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
        collect.setCollectTime(new Date());
        collect.setState(0);
        collect.setUser(user);

        collect.setHouse(house);

        //向数据库添加一条收藏记录并返回受影响行数
        int num = mapper.insert(collect);

        System.out.println(num);

        return num;
    }

    /**
     * 根据收藏id删除收藏
     */
    @Override
    public int deleteCollectByCollectId(Integer CollectId) {

        //删除记录并返回受影响行数
        int num = mapper.deleteInfoById(CollectId);

        return num;
    }

    /**
     * 根据用户id查看收藏房屋信息
     */
    @Override
    public int queryInfoByUser_id(Integer userId) {

        List<Collect> list = mapper.selectAllInfoByUserId(1);

        for (Collect collect:list) {
            System.out.println(collect.getHouse().getHouseName());
            System.out.println(collect.getUser().getUserName());
        }

        return 0;
    }
}
