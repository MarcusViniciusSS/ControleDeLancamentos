package domain.valueobjects

import domain.interfaces.IDinheiro
import domain.interfaces.ITipoLancamento

class Dinheiro (val value: Double, val tipoLancamento: ITipoLancamento) : IDinheiro {
    override fun getValor() : Double {
        return value * tipoLancamento.getPolicy()
    }
}
