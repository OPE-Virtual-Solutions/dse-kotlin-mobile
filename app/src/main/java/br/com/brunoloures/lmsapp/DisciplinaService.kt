package br.com.brunoloures.lmsapp

import android.content.Context

object DisciplinaService {
    fun getDisciplinas(context: Context): List<Disciplina> {
        val disciplinas = mutableListOf<Disciplina>()
// criar 10 disciplinas
        for (i in 1..10) {
            val d = Disciplina()
            d.nome = "Pedido $i"
            d.ementa = "Ementa Disciplina $i"
            d.professor = "Professor Disciplina $i"
            d.foto = "http://f.i.uol.com.br/folha/comida/images/10272699.jpeg"
            disciplinas.add(d)
        }
        return disciplinas
    }
}