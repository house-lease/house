package cn.bdqn.service;

import cn.bdqn.domain.User;

/**
 * 用户业务层
 */
public interface UserService {

    /**
     * 根据openId查询用户
     * @param openId
     * @return
     */
    public User queryByOpenId(String openId);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    public User queryByUserId(Integer userId);

    /**
     * 添加用户
     * @param user
     */
    public void save(User user);


    /**
     * 登录方法
     * @param code
     * @param image_url
     * @param nickName
     * @param sex
     * @return
     */
    public User login( String code,String image_url,String nickName,Integer sex);


}
