package cn.bdqn.service;

import cn.bdqn.domain.Record;
/**
 * 订单业务层
 */
public interface RecordService {
    /**
     * 保存订单
     * @param record
     */
    void save(Record record);
}
