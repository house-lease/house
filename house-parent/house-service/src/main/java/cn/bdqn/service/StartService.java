package cn.bdqn.service;

import cn.bdqn.domain.Start;

import java.util.List;

public interface StartService {

    public List<Start> queryAll();

    /**
     * 根据起租时间查询
     * @param startValue
     * @return
     */
    public List<Start> queryByStartValue(Integer startValue);
}
