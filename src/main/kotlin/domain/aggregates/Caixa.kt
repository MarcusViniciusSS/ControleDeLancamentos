package domain.aggregates

import domain.entities.Lancamento
import domain.valueobjects.StatusCaixa
import kotlinx.datetime.*
import kotlinx.serialization.Serializable

@Serializable
data class Caixa (var nome: String) {

    var id: Int = 0

    private var dataAbertura: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        private set

    private var dataFechamento: LocalDateTime? = null
        private set
    private var status: StatusCaixa = StatusCaixa.ABERTO
        private set
    private var lancamentos: MutableList<Lancamento> = mutableListOf()
        private set

    fun fechar() {
        this.dataFechamento = Clock.System.now().toLocalDateTime(TimeZone.UTC)
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