package com.liuxl.cartmall.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//@EnableWebSocketMessageBroker注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。 
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 订阅Broker名称
		registry.enableSimpleBroker("/topic");
		// 全局使用的消息前缀（客户端订阅路径上会体现出来）
		registry.setApplicationDestinationPrefixes("/app");
		// 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
		// registry.setUserDestinationPrefix("/user/");
	}

	// registerStompEndpoints方法表示注册STOMP协议的节点，并指定映射的URL。
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。
		registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
	}

}
