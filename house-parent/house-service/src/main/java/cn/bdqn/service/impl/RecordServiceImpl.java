package cn.bdqn.service.impl;

import cn.bdqn.domain.Record;
import cn.bdqn.mapper.RecordMapper;
import cn.bdqn.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public void save(Record record) {
        recordMapper.insert(record);
    }

//    根据付款用户id查询
    @Override
    public List<Record> queryByPayerUserId(Integer payerUserId) {
        return recordMapper.selectByPayerUserId(payerUserId);
    }

//    根据订单id修改订单支付状态
    @Override
    public List<Record> updateDealState(Integer userId,Integer id, Integer dealState) {
//        修改订单状态
        recordMapper.updateDealState(id,dealState);
//        查询最新订单列表
        return recordMapper.selectByPayerUserId(userId);
    }
}
