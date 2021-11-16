package br.com.brunoloures.lmsapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "pedidos")
class Disciplina: Serializable {

    @PrimaryKey
    var id:Long? = null
    var nome = ""
    var produto = ""
    var foto = ""
    var preco = ""

    override fun toString(): String {
        return "Teste(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}