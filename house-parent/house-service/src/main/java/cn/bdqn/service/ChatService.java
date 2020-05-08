package cn.bdqn.service;

import cn.bdqn.domain.Chat;

import java.util.List;

public interface ChatService {

    /**
     * 根据发送和接收的用户id还有发送消息添加记录
     */
    public void addInfoBySendUserIdAndReceptionUserId(Integer sendUserId,Integer receptionUserId,String message);

    /**
     *根据发送和接收的用户id查看记录根据时间排序
     */
    public List<Chat> selectAllInfoBySendUserIdAndReceptionUserId(Integer sendUserId, Integer receptionUserId);

    /**
     *根据记录id删除记录（list类型）
     */
    public void deleteInfoByChatId(List<Integer> chatIds);

}
