package br.com.brunoloures.lmsapp

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*


class telaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Leandro Lanches"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()

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

        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Botão de Buscar", Toast.LENGTH_SHORT).show()
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

    private fun configuraMenuLateral() {
        var toggle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.abrir,
            R.string.fechar
        )

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_disciplina -> {
                Toast.makeText(this, "Disciplinas", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_sair -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Sair do aplicativo.")
                builder.setMessage("Deseja realmente sair do app?")
                builder.setPositiveButton("Sim"){dialog, which ->
                    this.finishAffinity();
                }
                builder.setNegativeButton("Não"){dialog,which ->

                }
                builder.setNeutralButton("Cancelar"){_,_ ->

                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }





}