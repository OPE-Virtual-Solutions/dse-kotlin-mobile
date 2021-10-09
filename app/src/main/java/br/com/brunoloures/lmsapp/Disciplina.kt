package br.com.brunoloures.lmsapp

import java.io.Serializable

class Disciplina: Serializable {
    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var professor = ""

    override fun toString(): String {
        return "Teste(nome='$nome')"
    }
}