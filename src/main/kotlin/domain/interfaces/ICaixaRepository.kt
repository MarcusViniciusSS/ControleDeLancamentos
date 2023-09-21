package domain.interfaces

import domain.aggregates.Caixa

interface ICaixaRepository {
    fun Criar(Caixa: Caixa): Boolean
    fun BuscarPorId(id: Int): Caixa?
    fun Atualizar(Caixa: Caixa): Boolean
    fun Deletar(id: Int): Boolean
}