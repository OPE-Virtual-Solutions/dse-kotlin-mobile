package br.com.brunoloures.lmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_disciplina.*

class DisciplinaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplina)

        val disciplina = intent.getSerializableExtra("disciplina") as Disciplina


        textView.text = disciplina.nome
        textView2.text = disciplina.produto
        textView3.text = disciplina.preco

        Toast.makeText(this, "${disciplina.nome}", Toast.LENGTH_SHORT).show()
    }
}