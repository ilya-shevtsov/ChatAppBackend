package com.chatapp.plugins

import com.chatapp.room.RoomController
import com.chatapp.routes.chatSocket
import com.chatapp.routes.getAllMessages
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    install(Routing){
        chatSocket(roomController)
        getAllMessages(roomController)
    }
}
