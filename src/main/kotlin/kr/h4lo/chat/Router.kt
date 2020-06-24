package kr.h4lo.chat

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Component
class Router(private val webSocketHandler: WebSocketHandler) {
    @Bean
    fun webSocketHandlerMapping() = SimpleUrlHandlerMapping(mapOf("/event-emitter" to webSocketHandler), 1)

    @Bean
    fun webSocketHandlerAdapter() = WebSocketHandlerAdapter()
}