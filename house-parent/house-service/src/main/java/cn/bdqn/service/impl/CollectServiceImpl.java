package cn.bdqn.service.impl;

import cn.bdqn.domain.Collect;
import cn.bdqn.domain.House;
import cn.bdqn.domain.HouseImage;
import cn.bdqn.domain.User;
import cn.bdqn.mapper.CollectMapper;
import cn.bdqn.mapper.HouseImageMapper;
import cn.bdqn.mapper.HouseMapper;
import cn.bdqn.mapper.UserMapper;
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


    //用户接口
    @Autowired
    private UserMapper userMapper;

    //房屋图片
    @Autowired
    private HouseImageMapper houseImageMapper;


    //房屋接口
    @Autowired
    private HouseMapper houseMapper;

    /**
     * 根据用户id和房屋id添加收藏
     */
    @Override
    public void insertCollectByUserIdAndHouseId(Integer userId, Integer houseId) {

       //根据id查询用户
        User user = userMapper.selectByPrimaryKey(userId);

        //根据房屋id查询房屋对象
        House house = houseMapper.selectByPrimaryKey(houseId);

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
         mapper.insert(collect);


    }

    /**
     * 根据收藏id删除收藏
     */
    @Override
    public void deleteCollectByCollectId(Integer CollectId) {

        //调用mapper层的根据收藏id“删除”记录的方法
         mapper.deleteInfoById(CollectId);


    }

    /**
     * 根据用户id查看收藏房屋信息
     */
    @Override
    public List<Collect> queryInfoByUser_id(Integer userId) {

        //调用mapper的根据用户id查询用户收藏记录的方法并返回该用户的全部收藏数据
        List<Collect> list = mapper.selectAllInfoByUserId(userId);
        //获取房屋图片
        for (Collect c: list) {
            List<HouseImage> houseImages = houseImageMapper.selectByHouseId(c.getHouse().getId());
            c.getHouse().setHouseImages(houseImages);
        }

        return list;
    }
}
