package com.example.springbootdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 广播式websocket配置
 */
@Configuration
@EnableWebSocketMessageBroker   //开启使用STOMP协议来传输基于代理的消息  控制器支持 @MessageMapping
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{


    /**
     * 注册STOMP协议的节点，并映射指定的URL
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("endpointWisely").withSockJS();
    }

    /**
     * 配置消息代理 （Message Broker)
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
    }
}
