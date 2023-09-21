package application.usecases

import domain.aggregates.Caixa
import domain.interfaces.ICaixaRepository

class BuscarCaixa (private val caixaRepository: ICaixaRepository) {

    fun execute(id: Int) : Caixa? = caixaRepository.GetById(id)
}