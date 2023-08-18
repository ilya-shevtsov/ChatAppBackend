package com.chatapp

import com.chatapp.di.mainModule
import com.chatapp.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)


@Suppress("unused")
fun Application.module() {
    install(Koin){
        modules(com.chatapp.di.mainModule)
    }
    configureSockets()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureSecurity()

}
