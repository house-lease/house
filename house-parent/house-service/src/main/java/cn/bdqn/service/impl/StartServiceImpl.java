package cn.bdqn.service.impl;

import cn.bdqn.domain.Start;
import cn.bdqn.mapper.StartMapper;
import cn.bdqn.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartServiceImpl implements StartService {

    @Autowired
    private StartMapper startMapper;

    /**
     * 查询全部起租时间
     * @return
     */
    @Override
    public List<Start> queryAll() {
        return startMapper.selectAll();
    }

    /**
     * 根据起租时间查询
     * @param startValue
     * @return
     */
    @Override
    public List<Start> queryByStartValue(Integer startValue) {
        return startMapper.selectByStartValue(startValue);
    }
}
