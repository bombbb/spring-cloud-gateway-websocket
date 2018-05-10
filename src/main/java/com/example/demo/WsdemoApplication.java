package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

@SpringBootApplication
public class WsdemoApplication {

	@Autowired
	private WebSocketHandler webSocketHandler;

	public static void main(String[] args) {
		SpringApplication.run(WsdemoApplication.class, args);
	}

	@Bean
	public HandlerMapping websocketmapping() {
		Map<String, WebSocketHandler> map = new HashMap<>();
	    map.put("/ws2", webSocketHandler);

	    SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
	    handlerMapping.setOrder(1);
	    handlerMapping.setUrlMap(map);
	    return handlerMapping;
	}

	@Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

	@Bean
	public RouteLocator wsGateway(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("ws", predicate -> predicate
						.path("/ws")
						.uri("ws://localhost:8080/ws2"))
				.build();
	}
}
