package domain.aggregates

import domain.entities.Lancamento
import domain.valueobjects.StatusCaixa
import java.util.*

data class Caixa (var nome: String) {

    var id: Int = 0
        private set
    private var dataAbertura: Date = Date()
        private set
    private var dataFechamento: Date? = null
        private set
    private var status: StatusCaixa = StatusCaixa.ABERTO
        private set
    private lateinit var lancamentos: MutableList<Lancamento>
        private set

    fun fechar() {
        this.dataFechamento = Date()
        this.status = StatusCaixa.FECHADO
    }

    fun adicionarLancamento(lancamento: Lancamento) : Boolean {
        return this.lancamentos.add(lancamento)
    }

    fun getSaldoAtual() : Double {
        return this.lancamentos.sumOf { it.getValor() }
    }

    fun getLancametos() : List<Lancamento> {
        return this.lancamentos
    }
}