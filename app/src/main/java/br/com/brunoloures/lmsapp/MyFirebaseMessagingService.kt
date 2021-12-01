package br.com.brunoloures.lmsapp

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService: FirebaseMessagingService() {
    // recebe o novo token criado
    private val context: Context get() = this
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("teste", "Novo token: $token")
// Guarda o token em Shared Preferences
        Prefs.setString("FB_TOKEN", token!!)
    }
    // recebe a notificação de push
    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        Log.d("teste", "onMessageReceived")
// verifica se a mensagem recebida do firebase é uma notificação
        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            val corpo = mensagemRemota?.notification?.body
            Log.d("teste", "Titulo da mensagem: $titulo")
            Log.d("teste", "Corpo da mensagem: $corpo")

            val intent = Intent(this, MainActivity::class.java)
            NotificationUtil.create(context, 1, intent, titulo!!, corpo!!)
        }
    }
}