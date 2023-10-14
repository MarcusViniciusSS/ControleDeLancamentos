package webapi.plugins

import application.usecases.BuscarCaixa
import application.usecases.CriarCaixa
import domain.aggregates.Caixa
import domain.interfaces.ICaixaRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import webapi.plugins.viewModels.CaixaViewModel

fun Application.configureRouting() {

    val criarCaixa by inject<CriarCaixa>()
    val buscarCaixa by inject<BuscarCaixa>()
    val repository by inject<ICaixaRepository>()

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
            val dto = call.receive<CaixaViewModel>()
            var result = criarCaixa.execute(Caixa(dto.nome))

            call.respond(HttpStatusCode.Created, result)
        }

        patch  ("caixa/{id}") {
            val dto = call.receive<CaixaViewModel>()
            val id : Int? = call.parameters["id"]?.toInt()
            val caixa = id?.let { buscarCaixa.execute(it) }
            if (caixa == null) {
                call.respond(HttpStatusCode.NotFound)
            }

            caixa?.nome = dto.nome
            caixa?.let { repository.Update(it) }

            call.respond(HttpStatusCode.OK, caixa ?: NullBody)
        }

        delete ("caixa/{id}") {
            val id : Int? = call.parameters["id"]?.toInt()
            val resultado = id?.let { repository.Delete(it) }

            when {
                resultado == true -> call.respond("Removido")
                else -> call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
