package br.com.brunoloures.lmsapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DisciplinaDAO {

    @Query("SELECT * FROM pedidos WHERE id=:id ")
    fun getById(id: Long): Disciplina?

    @Query("SELECT * FROM pedidos")
    fun findAll(): List<Disciplina>

    @Insert
    fun insert(disciplina: Disciplina)

    @Delete
    fun delete(disciplina: Disciplina)
}