package cn.bdqn.service;

import cn.bdqn.domain.Apply;
import cn.bdqn.domain.User;

import java.util.List;

public interface ApplyService {

    /**
     * 添加房东认证信息
     */
    public boolean addInfo(Apply apply);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    public List<Apply> queryByUserId(Integer userId);
}
