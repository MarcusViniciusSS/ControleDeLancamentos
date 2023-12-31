package infrastructure.repositories

import domain.aggregates.Caixa
import domain.interfaces.ICaixaRepository

class CaixaRepository : ICaixaRepository {

    var caixaStorage = mutableListOf<Caixa>()

    override fun Create(caixa: Caixa): Int {
        caixa.id = caixaStorage.count() + 1
        caixaStorage.add(caixa)
        return caixa.id
    }

    override fun GetById(id: Int): Caixa? {
        return caixaStorage.find { it.id == id }
    }

    override fun Update(Caixa: Caixa): Boolean {
        val index = caixaStorage.indexOfFirst { it.id == Caixa.id }
        if (index == -1) return false
        caixaStorage[index] = Caixa
        return true
    }

    override fun Delete(id: Int): Boolean {
        val index = caixaStorage.indexOfFirst { it.id == id }
        if (index == -1) return false
        caixaStorage.removeAt(index)
        return true
    }
}