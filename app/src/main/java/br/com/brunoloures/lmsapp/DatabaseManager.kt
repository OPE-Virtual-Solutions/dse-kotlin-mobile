package br.com.brunoloures.lmsapp

import androidx.room.Room

object DatabaseManager {
    private var dbInstance: LeandroLanchesDataBase

    init {
        val context = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            context,
            LeandroLanchesDataBase::class.java,
            "lms.sqlite"
        ).build()
    }

    fun getDisciplinaDAO(): DisciplinaDAO {
        return dbInstance.disciplinaDAO()
    }
}