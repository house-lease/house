package cn.bdqn.controller;


import cn.bdqn.domain.ChatList;
import cn.bdqn.domain.ChatTest;
import cn.bdqn.service.ChatListService;
import cn.bdqn.service.ChatTestService;
import cn.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chatTest")
public class ChatTestController {

    @Autowired
    private ChatTestService chatTestService;

    @Autowired
    private ChatListService chatListService;

//    查询该用户的全部聊天列表
    @RequestMapping("/queryAllChatList")
    @ResponseBody
    public Result queryAllChatList(Integer userId){

        Result result = new Result();
        List<ChatList> chatLists = new ArrayList<>();
        try {
//            查询该用户的全部聊天列表
            chatLists = chatListService.queryAll(userId);
            result.setData(chatLists);
            result.setMessage("查询成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询失败");
            return result;
        }
    }


//    查询用户的聊天记录
    @RequestMapping("/queryAllChat")
    @ResponseBody
    public Result queryAllChat(Integer sendUserId, Integer receptionUserId){
        Result result = new Result();
        List<ChatTest> chatTests = new ArrayList<>();
        try {
            chatTests = chatTestService.queryByChatTest(sendUserId,receptionUserId);
            result.setMessage("查询成功");
            result.setData(chatTests);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("查询失败");
            return result;
        }

    }

//    修改状态
    @RequestMapping("/updateChatListViewState")
    @ResponseBody
    public Result updateChatListViewState(Integer sendUserId, Integer receptionUserId,Integer chatListId){

        Result result = new Result();

        try {
//            更新
            chatListService.updateChatList(sendUserId,receptionUserId,chatListId);
            result.setData(true);
            result.setMessage("更新成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setData(false);
            result.setMessage("更新失败");
            return result;
        }
    }
}
