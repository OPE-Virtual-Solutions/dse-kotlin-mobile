package br.com.brunoloures.lmsapp

import android.content.Context
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

class NovoPedido : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_pedido)

        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
        recyclerDisciplinas?.setHasFixedSize(true)
        recyclerDisciplinas?.visibility = View.VISIBLE




    }

    override fun onResume() {
        super.onResume()
        taskDisciplinas()
    }
    fun taskDisciplinas() {
        disciplinas = DisciplinaService.getDisciplinas(context)
        recyclerDisciplinas?.adapter = DisciplinaAdapter(disciplinas) {onClickDisciplina(it)}
    }
    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou disciplina ${disciplina.nome}", Toast.LENGTH_SHORT)
            .show()
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}