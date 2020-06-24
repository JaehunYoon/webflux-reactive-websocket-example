package kr.h4lo.chat

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class ReactiveWebSocketHandler : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        return session.send(Flux.interval(Duration.ofSeconds(1))
                .map { it.toString() }
                .map(session::textMessage))
                .and(session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .log())
    }
}