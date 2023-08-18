package com.chatapp.data

import com.chatapp.data.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageDataSourceImpl(
    private val db: CoroutineDatabase
) : MessageDataSource {

    private val messages = db.getCollection<Message>()
    override suspend fun getAllMassages(): List<Message> {
        return messages.find()
            .descendingSort(Message::timestamp)
            .toList()
    }

    override suspend fun insertMassage(message: Message) {
        messages.insertOne(message)
    }

}