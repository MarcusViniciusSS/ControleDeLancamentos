package domain.valueobjects

import domain.interfaces.ITipoLancamento

class Despesa : ITipoLancamento {
    override fun getPolicy(): Int = -1
}