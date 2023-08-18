package com.chatapp.data

import com.chatapp.data.model.Message

interface MessageDataSource {

    suspend fun getAllMassages(): List<Message>

    suspend fun insertMassage(message: Message)
}