package cn.bdqn.controller;


import cn.bdqn.utils.MyWebSocketInterceptor;
import cn.bdqn.utils.WebSocketPushHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component("webSocketConfigurer")
@EnableWebSocket
public class ChatController implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketInterceptor myWebSocketInterceptor;

    @Autowired
    private WebSocketPushHandler webSocketPushHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(webSocketPushHandler,"/wx").addInterceptors(myWebSocketInterceptor).setAllowedOrigins("*");

    }
}
