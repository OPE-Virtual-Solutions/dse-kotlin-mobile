package br.com.brunoloures.lmsapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_novo_pedido.*
import kotlinx.android.synthetic.main.activity_pedidos.*

class Pedidos : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
        recyclerDisciplinas?.setHasFixedSize(true)
        recyclerDisciplinas?.visibility = View.VISIBLE


    }

    override fun onResume() {
        super.onResume()
        taskDisciplinas()
    }

    var disciplinas = listOf<Disciplina>()
    fun taskDisciplinas() {
        Thread {
            this.disciplinas = DisciplinaService.getDisciplinas(this)
            runOnUiThread {
                recyclerDisciplinas?.adapter =
                    DisciplinaAdapter(disciplinas) { onClickDisciplina(it) }
            }
        }.start()
    }

    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou no pedido ${disciplina.nome}", Toast.LENGTH_SHORT)
            .show()
        val intent = Intent(this, DisciplinaActivity::class.java)
        intent.putExtra("disciplina", disciplina)
        startActivity(intent)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}