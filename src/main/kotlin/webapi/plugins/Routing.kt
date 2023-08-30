package webapi.plugins

import application.usecases.AdicionarLancamentos
import application.usecases.CriarCaixa
import domain.aggregates.Caixa
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val criarCaixa by inject<CriarCaixa>()

    routing {
        get {
            criarCaixa.execute(Caixa("123"))
            call.respondText("Hello World!")
        }
    }
}
