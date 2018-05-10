package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session
				.receive()
				.doOnNext(arg0 -> {
					System.out.println(arg0);

				})
				.log()
				.flatMap(in -> session.send(Mono.just(session.textMessage("hello back"))))
				.doFinally(sig -> System.out.println("Web socket session finished: " + sig.name()))
				.then();
	}
}
