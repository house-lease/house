package cn.bdqn.chat;

import cn.bdqn.domain.User;
import cn.bdqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 此类用来获取登录用户信息并交由websocket管理
 */
@Component
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse arg1, WebSocketHandler arg2,
                                   Map<String, Object> arg3) throws Exception {
        try{

            // 将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
            if (request instanceof ServletServerHttpRequest) {
                ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                HttpServletRequest httpRequest = servletRequest.getServletRequest();
                String userId= httpRequest.getHeader("user");
                User user = userService.queryByUserId(Integer.parseInt(userId));

                arg3.put("user",user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("连接到我了");

        return true;
    }
    @Override
    public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
        // TODO Auto-generated method stub

    }

}