package com.chatapp.room

import io.ktor.websocket.*

data class Memeber(
    val username: String,
    val sessionId: String,
    val socket: WebSocketSession

)
