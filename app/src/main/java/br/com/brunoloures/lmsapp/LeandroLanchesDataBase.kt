package br.com.brunoloures.lmsapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Disciplina::class), version = 1)
abstract class LeandroLanchesDataBase: RoomDatabase() {
    abstract fun disciplinaDAO() : DisciplinaDAO
}