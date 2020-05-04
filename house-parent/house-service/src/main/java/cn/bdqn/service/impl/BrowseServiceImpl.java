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

        User user = new User();
        user.setId(userId);

        House house = new House();
        house.setId(houseId);

        Browse browse = new Browse();
        browse.setState(0);
        browse.setBrowseTime(new Date());
        browse.setUser(user);
        browse.setHouse(house);

        int num =  mapper.insert(browse);

        return num;
    }

    /**
     * 根据收藏id删除浏览记录
     */
    @Override
    public int deleteBrowseByBrowseId(Integer browseId) {

        int num = mapper.deleteInfoById(1);

        return num;
    }

    /**
     * 根据用户id查看浏览记录
     */
    @Override
    public int queryInfoByUser_id(Integer userId) {

        List<Browse> list = mapper.selectAllInfoByUserId(1);

        for (Browse browse:list) {
            System.out.println(browse.getHouse().getHouseName());
            System.out.println(browse.getUser().getUserName());
        }

        return 0;
    }
}
