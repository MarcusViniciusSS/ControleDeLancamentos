package infrastructure.repositories

import domain.aggregates.Caixa
import domain.interfaces.ICaixaRepository

class CaixaRepository : ICaixaRepository {

    var caixaStorage = mutableListOf<Caixa>()

    override fun Criar(Caixa: Caixa): Boolean {
        return caixaStorage.add(Caixa)
    }

    override fun BuscarPorId(id: Int): Caixa? {
        return caixaStorage.find { it.id == id }
    }

    override fun Atualizar(Caixa: Caixa): Boolean {
        val index = caixaStorage.indexOfFirst { it.id == Caixa.id }
        if (index == -1) return false
        caixaStorage[index] = Caixa
        return true
    }
}