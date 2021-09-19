package br.com.brunoloures.lmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {
    class Usuario(val email: String, val senha: String);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        var credenciais = Usuario("aluno", "impacta");
        var inputEmail = findViewById<EditText>(R.id.campo_usuario);
        var inputSenha = findViewById<EditText>(R.id.campo_senha);
        var btnEntrar = findViewById<Button>(R.id.botao_login);

        btnEntrar.setOnClickListener(View.OnClickListener {
            var usuario = Usuario(email = inputEmail.text.toString(), senha = inputSenha.text.toString());
            this.efetuarLogin(usuario = usuario, credenciais = credenciais);

        });
    }

    private fun efetuarLogin(usuario: Usuario, credenciais: Usuario) {
        if (usuario.email.isEmpty() || usuario.senha.isEmpty()) {
            this.exibirMensagemDeErro();
        };

        if (usuario.email == credenciais.email && usuario.senha == credenciais.senha) {
            this.exibirMensagemDeSucesso();
        } else {
            this.exibirMensagemDeErro();
        }
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