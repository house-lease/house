package cn.bdqn.chat;

import cn.bdqn.domain.Chat;
import cn.bdqn.domain.ChatList;
import cn.bdqn.domain.ChatTest;
import cn.bdqn.domain.User;
import cn.bdqn.service.ChatListService;
import cn.bdqn.service.ChatService;
import cn.bdqn.service.ChatTestService;
import cn.bdqn.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WebSocketPushHandler implements WebSocketHandler {

//    储存用户的集合
    private static final List<WebSocketSession> users = new ArrayList<>();

    @Autowired
    private ChatTestService chatTestService;

    @Autowired
    private ChatListService chatListService;
    @Autowired
    private UserService userService;
    // 用户进入系统监听
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        users.add(session);
        User user =(User) session.getAttributes().get("user");
        System.out.println("成功进入了系统。。。");
        System.out.println(user);


    }

    //
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 将消息进行转化，因为是消息是json数据，可能里面包含了发送给某个人的信息，所以需要用json相关的工具类处理之后再封装成TextMessage，
        // 我这儿并没有做处理，消息的封装格式一般有{from:xxxx,to:xxxxx,msg:xxxxx}，来自哪里，发送给谁，什么消息等等
        System.out.println(message.getPayload());

//        封装聊天数据
        ChatTest chat = new ChatTest();
        JSONObject jsonObject = JSONObject.parseObject(message.getPayload().toString());
        chat.setMessage((String)jsonObject.get("message"));
        chat.setSendTime(new Date());
        chat.setState(0);
        String sendUserId = (String) jsonObject.get("sendUser");
        String receptionUser = (String)jsonObject.get("receptionUser");
        String isMyYou = (String)jsonObject.get("isMyYou");
        chat.setIsMyYou(Integer.parseInt(isMyYou));
        boolean panDuan= false;
        chat.setSendUser(userService.queryByUserId(Integer.parseInt(sendUserId)));
        chat.setReceptionUser(userService.queryByUserId(Integer.parseInt(receptionUser)));
        for (WebSocketSession webSocketSession : users) {
            User user = (User)webSocketSession.getAttributes().get("user");
            if (user.getId()==chat.getReceptionUser().getId()){
                panDuan = true;
                break;
            }
        }
        if (!panDuan){
            chat.setViewState(1);
        }else {
            chat.setViewState(0);
        }
//        添加聊天记录
        chatTestService.save(chat);

//        判断当前发送方用户和接收方用户是否创建会话列表
        ChatList chatList = chatListService.queryByChatList(chat.getSendUser().getId(),chat.getReceptionUser().getId());
        if (chatList!=null){
            chatList.setMessage(chat.getMessage());
            chatList.setUnread(1);
            chatList.setSendTime(new Date());
            chatListService.updateChat(chatList);
        }else {
            chatList = new ChatList();
            chatList.setSendUser(chat.getSendUser());
            chatList.setReceptionUser(chat.getReceptionUser());
            chatList.setMessage(chat.getMessage());
            chatList.setSendTime(new Date());
            chatList.setState(0);
            chatList.setUnread(1);
//        添加会话列表
            chatListService.save(chatList);
        }
        Gson gson = new Gson();
        System.out.println(chat);
        // 给所有用户群发消息
        //sendMessagesToUsers(msg);
         //给指定用户群发消息
        System.out.println("==========");
        sendMessageToUser(chat.getReceptionUser().getId(), new TextMessage(gson.toJson(chat)));
    }

    // 后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    // 用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        users.remove(session);
        System.out.println("安全退出了系统");

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有的用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息给指定的用户
     */
    public void sendMessageToUser(Integer userId, TextMessage message) {
        for (WebSocketSession user : users) {
            User user1 = (User) user.getAttributes().get("user");
            if (user1.getId()==userId) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}