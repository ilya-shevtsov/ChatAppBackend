package com.chatapp.room

import com.chatapp.data.MessageDataSource
import com.chatapp.data.model.Message
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(
    private val messageDataSource: MessageDataSource
) {
    private val members = ConcurrentHashMap<String, Memeber>()

    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ) {
        if (members.containsKey(username)) {
            throw MemberAlreadyExistsException()
        }
        members[username] = Memeber(
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }

    suspend fun sendMessage(
        senderUsername: String,
        message: String
    ) {
        members.values.forEach { member ->
            val messageEntity = Message(
                text = message,
                username = senderUsername,
                timestamp = System.currentTimeMillis()
            )
            messageDataSource.insertMassage(messageEntity)

            val parsedMessage = Json.encodeToString(messageEntity)
            member.socket.send(Frame.Text(parsedMessage))
        }
    }

    suspend fun getAllMessages(): List<Message>{
        return messageDataSource.getAllMassages()
    }

    suspend fun tryDisconnect(username: String){
        members[username]?.socket?.close()
        if(members.containsKey(username)){
            members.remove(username)
        }
    }
}