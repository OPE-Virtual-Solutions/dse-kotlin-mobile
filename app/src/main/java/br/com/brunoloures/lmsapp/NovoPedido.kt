package br.com.brunoloures.lmsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_novo_pedido.*
import kotlinx.android.synthetic.main.login.*

class NovoPedido : DebugActivity()  {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_pedido)

        button.setOnClickListener(View.OnClickListener {
           var foto = "https://conteudo.imguol.com.br/c/entretenimento/8c/2018/04/18/fast-food-lanche-1524056858988_v2_450x450.jpg"
           var preco = editTextTextPersonName4.text.toString()
           var nome = editTextTextPersonName2.text.toString()
           var produto = editTextTextPersonName3.text.toString()

//            val rootObject= JSONObject()
//            rootObject.put("foto", foto)
//            rootObject.put("preco", preco)
//            rootObject.put("nome", nome)
//            rootObject.put("produto", produto)

            val disciplina = Disciplina()
            disciplina.foto="https://conteudo.imguol.com.br/c/entretenimento/8c/2018/04/18/fast-food-lanche-1524056858988_v2_450x450.jpg"
            disciplina.preco = preco
            disciplina.nome = nome
            disciplina.produto = produto
            taskAtualizar(disciplina)
        })
    }

    fun taskAtualizar(disciplina: Disciplina) {
        Thread {
            DisciplinaService.save(disciplina)
            runOnUiThread {
                enviaNotificacao(disciplina)
                finish()
            }
        }.start()


    }

    fun enviaNotificacao(disciplina: Disciplina){
        val intent = Intent(this, Pedidos::class.java)
        intent.putExtra("disciplina", disciplina)
        NotificationUtil.create(context, 1, intent, "LMSApp", "Cadastro do pedido de ${disciplina.nome} realizado com sucesso!")
    }
}