package cn.bdqn.service.impl;

import cn.bdqn.domain.Particular;
import cn.bdqn.mapper.ParticularMapper;
import cn.bdqn.service.ParticularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class ParticularServiceImpl implements ParticularService {
    @Autowired
    private ParticularMapper particularMapper;
    //添加充值记录
    @Override
    public void save(Particular record) {
     particularMapper.insert(record);
    }

    //删除充值记录
    @Override
    public int delete(Integer id) {
        return particularMapper.updateById(id);
    }

    @Override
    public Particular selecById(Integer id) {
        return particularMapper.selectByPrimaryKey(id);
    }

}
