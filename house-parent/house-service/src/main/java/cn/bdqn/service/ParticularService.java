package cn.bdqn.service;

import cn.bdqn.domain.Particular;

public interface ParticularService {
   //添加充值记录
    void save(Particular record);
    //删除充值记录
    int delete (Integer id);

    //根据id查询充值记录
    Particular  selecById(Integer id);
}
