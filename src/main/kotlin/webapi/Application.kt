package webapi

import application.usecases.BuscarCaixa
import application.usecases.CriarCaixa
import domain.interfaces.ICaixaRepository
import infrastructure.repositories.CaixaRepository
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import webapi.plugins.configureDatabases
import webapi.plugins.configureHTTP
import webapi.plugins.configureRouting
import webapi.plugins.configureSerialization

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(appModule)
    }

    configureSerialization()
    configureHTTP()
    configureDatabases()
    configureRouting()
}


val appModule = module {
    singleOf(::CaixaRepository) { bind<ICaixaRepository>() }
    single { CriarCaixa(get()) }
    single { BuscarCaixa(get()) }
}