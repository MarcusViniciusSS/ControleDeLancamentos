package domain.interfaces

import domain.aggregates.Caixa

interface ICaixaRepository {
    fun Create(Caixa: Caixa): Boolean
    fun GetById(id: Int): Caixa?
    fun Update(Caixa: Caixa): Boolean
    fun Delete(id: Int): Boolean
}