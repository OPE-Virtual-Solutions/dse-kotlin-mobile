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

        campo_usuario.setText(Prefs.getString("nome_usuario"))
        campo_senha.setText(Prefs.getString("senha_usuario"))
        checkBox.isChecked = Prefs.getBoolean("lembrar_login")



        var credenciais = Usuario("aluno", "impacta");
        var inputEmail = findViewById<EditText>(R.id.campo_usuario);
        var inputSenha = findViewById<EditText>(R.id.campo_senha);
        var btnEntrar = findViewById<Button>(R.id.botao_login);

        btnEntrar.setOnClickListener(View.OnClickListener {

            val nome_usuario = campo_usuario.text.toString()
            val senha_usuario = campo_senha.text.toString()
            val check_login = checkBox.isChecked

            if (check_login) {
                Prefs.setString("nome_usuario", nome_usuario)
                Prefs.setString("senha_usuario", senha_usuario)
            } else{
                Prefs.setString("nome_usuario", "")
                Prefs.setString("senha_usuario", "")
            }
            Prefs.setBoolean("lembrar_login", check_login)


            var cont = 0
            var login = listOf<Login>()
            Thread {
                login = LoginService.getLogin(this)
                runOnUiThread {
                    for(nome in login) {
                        if (AndroidUtils.isInternetDisponivel(LMSApplication.getInstance().applicationContext)) {

                            if (nome.fullName == campo_usuario.text.toString() && nome.password == campo_senha.text.toString()) {
                                cont = 1
                                exibirMensagemDeSucesso();
                                Log.d("SUCESSO DO IF", "Entrou no if")
                            }
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