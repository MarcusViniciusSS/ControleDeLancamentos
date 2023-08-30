package domain.valueobjects

import domain.interfaces.ITipoLancamento

class Receita : ITipoLancamento {
    override fun getPolicy(): Int = 1
}