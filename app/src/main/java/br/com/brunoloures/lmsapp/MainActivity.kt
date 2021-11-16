package br.com.brunoloures.lmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.util.Log
import kotlinx.android.synthetic.main.activity_novo_pedido.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {
    class Usuario(val email: String, val senha: String);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val nome_usuario = campo_usuario.text.toString()
        val senha_check = campo_senha.text.toString()

        Prefs.setBoolean("lembrar", checkBox.isChecked)
        if (checkBox.isChecked) {
            Prefs.setString("lembrarNome", nome_usuario)
            Prefs.setString("lembrarSenha", senha_check)
        } else{
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")
            campo_usuario.setText(lembrarNome)
            campo_senha.setText(lembrarSenha)
            checkBox.isChecked = lembrar
        }

        var credenciais = Usuario("aluno", "impacta");
        var inputEmail = findViewById<EditText>(R.id.campo_usuario);
        var inputSenha = findViewById<EditText>(R.id.campo_senha);
        var btnEntrar = findViewById<Button>(R.id.botao_login);

        btnEntrar.setOnClickListener(View.OnClickListener {

            var cont = 0
            var login = listOf<Login>()
            Thread {
                login = LoginService.getLogin(this)
                runOnUiThread {
                    for(nome in login){
                        if(nome.fullName == campo_usuario.text.toString() && nome.password == campo_senha.text.toString()) {
                            cont = 1
                            exibirMensagemDeSucesso();
                            Log.d("SUCESSO DO IF", "Entrou no if")
                        }
                    }
                    if(cont != 1){
                        exibirMensagemDeErro()
                    }
                }
            }.start()

        });
    }


    private fun exibirMensagemDeErro() {
        Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
    }

    private fun exibirMensagemDeSucesso() {
        Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_LONG).show();
        var intent = Intent(this, telaInicialActivity::class.java)

        startActivity(intent)

    }

}