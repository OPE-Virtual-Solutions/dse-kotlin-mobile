package br.com.brunoloures.lmsapp

import android.content.Context

object DisciplinaService {
    fun getDisciplinas(context: Context): List<Disciplina> {
        val disciplinas = mutableListOf<Disciplina>()
// criar 10 disciplinas
        for (i in 1..12) {
            val d = Disciplina()
            d.nome = "Serviço agendado $i"
            d.ementa = "Ementa Disciplina $i"
            d.professor = "Professor Disciplina $i"
            d.foto = "https://wp-buyco-2021.s3.amazonaws.com/wp-content/uploads/2020/10/aquisicao-de-empresas.png"
            disciplinas.add(d)
        }
        return disciplinas
    }
}