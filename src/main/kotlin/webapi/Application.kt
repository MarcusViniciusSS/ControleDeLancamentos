package webapi

import application.usecases.BuscarCaixa
import application.usecases.CriarCaixa
import domain.interfaces.ICaixaRepository
import infrastructure.repositories.CaixaRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import webapi.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(appModule)
    }

    install(StatusPages) {
        exception<RequestValidationException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString() )
        }
    }

    configureSerialization()
    configureHTTP()
    configureDatabases()
    configureRouting()
    configureValidationModel()
}


val appModule = module {
    singleOf(::CaixaRepository) { bind<ICaixaRepository>() }
    single { CriarCaixa(get()) }
    single { BuscarCaixa(get()) }
}