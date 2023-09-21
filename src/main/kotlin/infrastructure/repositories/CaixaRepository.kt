package infrastructure.repositories

import domain.aggregates.Caixa
import domain.interfaces.ICaixaRepository

class CaixaRepository : ICaixaRepository {

    var caixaStorage = mutableListOf<Caixa>()

    override fun Criar(caixa: Caixa): Boolean {
        caixa.id = caixaStorage.count() + 1
        return caixaStorage.add(caixa)
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

    override fun Deletar(id: Int): Boolean {
        val index = caixaStorage.indexOfFirst { it.id == id }
        if (index == -1) return false
        caixaStorage.removeAt(index)
        return true
    }
}