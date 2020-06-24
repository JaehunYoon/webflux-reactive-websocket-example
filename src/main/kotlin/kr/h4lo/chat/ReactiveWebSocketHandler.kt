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

    /**
     * Kotlin Webflux Client Example Code
     */

    /*
    ReactorNettyWebSocketClient().execute(URI("ws://localhost:8080/event-emitter")) { session ->
        session.send(session.textMessage("foo").toMono())
                .thenMany(session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .log()
                ).then()
    }
    .block(Duration.ofSeconds(10))
     */
}