package application.usecases

import domain.entities.Lancamento

class AdicionarLancamentos {
    // adicionar injecao dependencia para o repositorio de caixa
    // publicar uma mensagem de dominio quando o lancamento for criado
    fun execute(idCaixa: String, list: List<Lancamento>) {
        println(idCaixa)
    }
}