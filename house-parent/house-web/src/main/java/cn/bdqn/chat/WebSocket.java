package cn.bdqn.chat;

import cn.bdqn.chat.MyWebSocketInterceptor;
import cn.bdqn.chat.WebSocketPushHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component("webSocketConfigurer")
@EnableWebSocket
public class WebSocket implements WebSocketConfigurer {

//    拦截器
    @Autowired
    private MyWebSocketInterceptor myWebSocketInterceptor;


//    消息处理器
    @Autowired
    private WebSocketPushHandler webSocketPushHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(webSocketPushHandler,"/wx").addInterceptors(myWebSocketInterceptor).setAllowedOrigins("*");

    }
}