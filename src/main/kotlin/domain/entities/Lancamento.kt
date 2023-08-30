package domain.entities

import domain.valueobjects.Dinheiro
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Lancamento (
    val description : String,
    val amount : Dinheiro,
    val Date: LocalDateTime
) {
    fun getValor() : Double {
        return amount.getValor()
    }
}




