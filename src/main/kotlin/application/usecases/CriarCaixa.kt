package application.usecases

import domain.aggregates.Caixa
import domain.interfaces.ICaixaRepository

class CriarCaixa (private val caixaRepository: ICaixaRepository) {
    // adicionar injecao dependencia para o repositorio de caixa
    // publicar uma mensagem de dominio quando o caixa for criado
    fun execute(caixa: Caixa) {
        println("Caixa criada ${caixa.nome}")
    }
}