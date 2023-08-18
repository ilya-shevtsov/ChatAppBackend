package com.chatapp.di

import com.chatapp.data.MessageDataSource
import com.chatapp.data.MessageDataSourceImpl
import com.chatapp.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.KMongo
import org.litote.kmongo.coroutine.coroutine

val mainModule = module {
    single {
        org.litote.kmongo.reactivestreams.KMongo.createClient()
            .coroutine
            .getDatabase("message_db_yt")
    }
    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single {
        RoomController(get())
    }
}