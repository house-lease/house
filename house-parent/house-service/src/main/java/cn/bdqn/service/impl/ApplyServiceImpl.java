package cn.bdqn.service.impl;

import cn.bdqn.domain.Apply;
import cn.bdqn.mapper.ApplyMapper;
import cn.bdqn.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("applyServiceImpl")
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper mapper;

    @Override
    public void addInfo(Apply apply) {

        mapper.insertInfo(apply);

    }
}
