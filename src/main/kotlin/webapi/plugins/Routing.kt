package webapi.plugins

import application.usecases.BuscarCaixa
import application.usecases.CriarCaixa
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.aggregates.Caixa
import domain.interfaces.ICaixaRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import webapi.plugins.viewModels.CaixaViewModel
import webapi.plugins.viewModels.UserViewModel
import java.util.*

fun Application.configureRouting(environment: ApplicationEnvironment) {

    val criarCaixa by inject<CriarCaixa>()
    val buscarCaixa by inject<BuscarCaixa>()
    val repository by inject<ICaixaRepository>()

    routing {

        post ("login") {
            val secret = environment.config.property("jwt.secret").getString()
            val issuer = environment.config.property("jwt.issuer").getString()
            val audience = environment.config.property("jwt.audience").getString()
            val myRealm = environment.config.property("jwt.realm").getString()

            val user = call.receive<UserViewModel>()

            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", user.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                .sign(Algorithm.HMAC256(secret))

            call.respond(hashMapOf("token" to token))
        }

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

        authenticate("auth-jwt") {
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
}
