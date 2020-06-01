package cn.bdqn.mapper;

import cn.bdqn.domain.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {


    int deleteByPrimaryKey(Integer id);

//    添加订单
    int insert(Record record);


//根据订单id查询
    Record selectByPrimaryKey(Integer id);

//    更新
    int updateByPrimaryKeySelective(Record record);

//    更新
    int updateByPrimaryKey(Record record);

//    根据付款用户id查询
    public List<Record> selectByPayerUserId(Integer payerUserId);

//    根据订单id修改状态
    public void updateDealState(@Param("id") Integer id,@Param("dealState") Integer dealState);

}