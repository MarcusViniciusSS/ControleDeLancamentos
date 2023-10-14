package webapi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import webapi.plugins.viewModels.CaixaViewModel

fun Application.configureValidationModel() {
    install(RequestValidation) {
        validate<CaixaViewModel> { viewModel ->
            if (viewModel.nome.length <= 5)
                ValidationResult.Invalid("O nome precisa ser maior que 5 caracteres.")
            else ValidationResult.Valid
        }
    }
}