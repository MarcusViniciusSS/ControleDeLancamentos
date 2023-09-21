package webapi.plugins

import application.usecases.AdicionarLancamentos
import application.usecases.BuscarCaixa
import application.usecases.CriarCaixa
import domain.aggregates.Caixa
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.reflect.typeOf

fun Application.configureRouting() {

    val criarCaixa by inject<CriarCaixa>()
    val buscarCaixa by inject<BuscarCaixa>()

    routing {
        get  {
            criarCaixa.execute(Caixa("123"))
            val caixa = buscarCaixa.execute(1)
            if (caixa == null) {
                call.respond(404)
            }
            
            call.respond(caixa ?: NullBody)
        }

    }
}
