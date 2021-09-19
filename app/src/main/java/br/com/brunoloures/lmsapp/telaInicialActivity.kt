package br.com.brunoloures.lmsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class telaInicialActivity : DebugActivity  () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)

        val args = intent.extras
//        val nome_pessoa = args?.getString("nome_pessoa")
//        Toast.makeText(this, "parametro 1: BrunoLoures", Toast.LENGTH_LONG).show()
        val nome_usuario = args?.getString("nome_usuario")
        campo_texto_exercio.text = nome_usuario

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Leadro Lanches"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_buscar) {
            // IMPLEMENTAR A LÓGICA NECESSÁRIA PARA TRATAMANTO DE EVENTO DO ITEM DE MENU
            Toast.makeText(this, "Botão de Buscar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Botão de Atualizar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(this, "Botão de Configuração", Toast.LENGTH_SHORT).show()
        } else if (id == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)

    }
}