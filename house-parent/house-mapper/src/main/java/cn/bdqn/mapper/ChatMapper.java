package cn.bdqn.mapper;

import cn.bdqn.domain.Chat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);

    /**
     * 根据发送和接收用户的id查询记录
     */
    public List<Chat> queryAllInfoBySendUserIdAndReceptionUserId(@Param("sendUserId")Integer sendUserId,@Param("receptionUserId") Integer receptionUserId);

    /**
     * 根据id删记录
     */
    public int deleteInfoById(List<Integer> ids);
}