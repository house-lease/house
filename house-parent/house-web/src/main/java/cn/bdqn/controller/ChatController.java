package cn.bdqn.controller;

import cn.bdqn.domain.Browse;
import cn.bdqn.domain.Chat;
import cn.bdqn.service.BrowseService;
import cn.bdqn.service.ChatService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 浏览功能的Controller
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService service;

    /**
     * 根据发送和接收的用户id还有发送消息添加记录
     * @param sendUserId
     * @param receptionUserId
     * @param message
     * @return
     */
    @RequestMapping("/addInfo")
    @ResponseBody
    public Result addInfo(Integer sendUserId, Integer receptionUserId,String message){

        //新建Result对象
        Result result = new Result();

        try {
            //调用service层的根据用户id和房屋id添加浏览记录的方法
            service.addInfoBySendUserIdAndReceptionUserId(sendUserId,receptionUserId,message);

            //设置返回消息为成功
            result.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();

            //设置返回消息为失败
            result.setMessage("添加失败");
        }

        return result;
    }

    /**
     * 根据记录id删除记录
     * @param chatIds
     * @return
     */
    @RequestMapping("/deleteInfo")
    @ResponseBody
    public Result deleteInfo(List<Integer> chatIds){

        //新建Result对象
        Result result = new Result();

        try{
            //调用service层的根据用户id和房屋id“删除”浏览记录的方法
            service.deleteInfoByChatId(chatIds);
            //设置返回消息为成功
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            //设置返回消息为失败
            result.setMessage("删除失败");
        }

        return result;
    }

    /**
     * 根据发送和接收的用户id查看记录根据时间排序
     * @param sendUserId
     * @param receptionUserId
     * @return
     */
    @RequestMapping("/queryInfo")
    @ResponseBody
    public Result queryInfo(Integer sendUserId, Integer receptionUserId){

        //新建Result对象
        Result result = new Result();

        List<Chat> lists = new ArrayList<>();
        try{
            //调用service层的根据用户id查询该用户全部浏览记录的方法
            lists = service.selectAllInfoBySendUserIdAndReceptionUserId(sendUserId,receptionUserId);

            //设置返回数据
            result.setData(lists);

            //设置返回消息
            result.setMessage("聊天记录");
            return result;
        }catch (Exception e){

            //设置返回数据
            result.setData(lists);
            //设置返回消息
            result.setMessage("失败");
            e.printStackTrace();
            return result;

        }




    }

}
