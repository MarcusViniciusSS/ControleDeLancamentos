package domain.valueobjects

import domain.interfaces.IDinheiro
import domain.interfaces.ITipoLancamento
import kotlinx.serialization.Serializable

@Serializable
class Dinheiro (val value: Double, val tipoLancamento: ITipoLancamento) : IDinheiro {
    override fun getValor() : Double {
        return value * tipoLancamento.getPolicy()
    }
}
