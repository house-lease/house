package cn.bdqn.service.impl;

import cn.bdqn.domain.Browse;
import cn.bdqn.domain.Collect;
import cn.bdqn.domain.House;
import cn.bdqn.domain.User;
import cn.bdqn.mapper.BrowseMapper;
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

    /**
     * 根据用户id和房屋id添加浏览记录
     */
    @Override
    public int insertBrowseByUserIdAndHouseId(Integer userId, Integer houseId) {

        //创建一个user对象存入用户id
        User user = new User();
        user.setId(userId);

        //创建一个房屋对象存入房屋id
        House house = new House();
        house.setId(houseId);

        //创建浏览记录对象，为添加方法准备数据
        Browse browse = new Browse();
        //给浏览时间字段赋值
        browse.setBrowseTime(new Date());
        //设置收藏状态
        browse.setState(0);
        //设置用户id
        browse.setUser(user);
        //设置房屋id
        browse.setHouse(house);

        //调用mapper层的添加记录方法
        int num =  mapper.insert(browse);

        return num;
    }

    /**
     * 根据收藏id删除浏览记录
     */
    @Override
    public int deleteBrowseByBrowseId(Integer browseId) {

        //调用mapper层的根据浏览记录id“删除”记录的方法
        int num = mapper.deleteInfoById(browseId);

        return num;
    }

    /**
     * 根据用户id查看浏览记录
     */
    @Override
    public List<Browse> queryInfoByUser_id(Integer userId) {

        //调用mapper层的根据用户id查询该用户的全部浏览记录方法
        List<Browse> list = mapper.selectAllInfoByUserId(userId);

        return list;
    }
}
