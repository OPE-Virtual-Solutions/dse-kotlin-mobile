package br.com.brunoloures.lmsapp

import android.content.Context

object DisciplinaService {
    fun getDisciplinas(context: Context): List<Disciplina> {
        val disciplinas = mutableListOf<Disciplina>()
// criar 10 disciplinas
        for (i in 1..10) {
            val d = Disciplina()
            d.nome = "Teste $i"
            d.ementa = "Ementa Disciplina $i"
            d.professor = "Professor Disciplina $i"
            d.foto = "http://s2.glbimg.com/VD9XgaIKvSsGQCXNttt1lZHHY9o=/695x0/s.glbimg.com/po/tt2/f/original/2015/09/28/android.jpg"
            disciplinas.add(d)
        }
        return disciplinas
    }
}