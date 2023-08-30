package domain.entities

import domain.valueobjects.Dinheiro
import java.util.Date

data class Lancamento (
    val description : String,
    val amount : Dinheiro,
) {
    val Date = Date()

    fun getValor() : Double {
        return amount.getValor()
    }
}




