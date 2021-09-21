package br.com.brunoloures.lmsapp

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*


class telaInicialActivity : DebugActivity  () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Leandro Lanches"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar.visibility = View.GONE

        var btnPedidos = findViewById<Button>(R.id.botao_pedidos)
        var btnNovoPedido = findViewById<Button>(R.id.botao_novo_pedido)
        var btnPerfil = findViewById<Button>(R.id.botao_perfil)

        btnPedidos.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Pedidos", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Pedidos::class.java)
            startActivity(intent)
        })

        btnNovoPedido.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Novo Pedidos", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, NovoPedido::class.java)
            startActivity(intent)
        })

        btnPerfil.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Pedidos::class.java)
            startActivity(intent)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_add) {
            var intent = Intent(this, Adicionar::class.java)
            startActivity(intent)
        } else if (id == R.id.action_atualizar) {
            progressBar.max = 1000
            progressBar.visibility = View.VISIBLE
            val currentProgress = 1000

            ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
                .setDuration(1000)
                .start()

            val timer = object: CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    progressBar.visibility = View.GONE;
                }
            }

            timer.start()

            if(progressBar.max < 100){
                progressBar.visibility = View.GONE
            }
        } else if (id == R.id.action_config) {
            Toast.makeText(this, "Configuração", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Config::class.java)
            startActivity(intent)

        } else if (id == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }


}