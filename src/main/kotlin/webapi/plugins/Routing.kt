package webapi.plugins

import application.usecases.AdicionarLancamentos
import application.usecases.BuscarCaixa
import application.usecases.CriarCaixa
import domain.aggregates.Caixa
import io.ktor.http.*
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
        get ("caixa/{id}") {
            val id : Int? = call.parameters["id"]?.toInt()
            val caixa = id?.let { buscarCaixa.execute(it) }
            if (caixa == null) {
                call.respond(HttpStatusCode.NotFound)
            }
            call.respond(caixa ?: NullBody)
        }

        post ("caixa") {

        }

        put ("caixa/{id}") {
//            val id = call.parameters["id"]
//            //val caixa = buscarCaixa.execute(id)/
//            if (caixa == null) {
//                call.respond(404)
//            }
//
//            call.respond(caixa ?: NullBody)
        }
    }
}
