package cn.bdqn.service.impl;

import cn.bdqn.domain.*;
import cn.bdqn.mapper.BrowseMapper;
import cn.bdqn.mapper.HouseImageMapper;
import cn.bdqn.mapper.HouseMapper;
import cn.bdqn.mapper.UserMapper;
import cn.bdqn.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("browseService")
public class BrowseServiceImpl implements BrowseService {

    //自动注入mapper
    @Autowired
    private BrowseMapper mapper;

    //用户接口
    @Autowired
    private UserMapper userMapper;


    //房屋接口
    @Autowired
    private HouseMapper houseMapper;

    //房屋图片
    @Autowired
    private HouseImageMapper houseImageMapper;

    /**
     * 根据用户id和房屋id添加浏览记录
     */
    @Override
    public void insertBrowseByUserIdAndHouseId(Integer userId, Integer houseId) {

//        判断该用户是否浏览过改房子
        Browse browse = mapper.selectByUserIdAndHouseId(userId,houseId);

        if (browse==null){
            //根据id查询用户
            User user = userMapper.selectByPrimaryKey(userId);
            //根据房屋id查询房屋对象
            House house = houseMapper.selectByPrimaryKey(houseId);
            //创建浏览记录对象，为添加方法准备数据
            browse = new Browse();
            //给浏览时间字段赋值
            browse.setBrowseTime(new Date());
            //设置收藏状态
            browse.setState(0);
            //设置用户id
            browse.setUser(user);
            //设置房屋id
            browse.setHouse(house);
            //调用mapper层的添加记录方法
            mapper.insert(browse);
        }


    }

    /**
     * 根据收藏id删除浏览记录
     */
    @Override
    public void deleteBrowseByBrowseId(Integer browseId) {

        //调用mapper层的根据浏览记录id“删除”记录的方法
        mapper.deleteInfoById(browseId);

    }

    /**
     * 根据用户id查看浏览记录
     */
    @Override
    public List<Browse> queryInfoByUser_id(Integer userId) {

        //调用mapper层的根据用户id查询该用户的全部浏览记录方法
        List<Browse> list = mapper.selectAllInfoByUserId(userId);

        //获取房屋图片
        for (Browse b: list) {
            List<HouseImage> houseImages = houseImageMapper.selectByHouseId(b.getHouse().getId());
            b.getHouse().setHouseImages(houseImages);
        }
        return list;
    }
}
