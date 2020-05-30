package cn.bdqn.service;

import cn.bdqn.domain.Apply;
import cn.bdqn.domain.User;

public interface ApplyService {

    /**
     * 添加房东认证信息
     */
    public boolean addInfo(Apply apply);

}
