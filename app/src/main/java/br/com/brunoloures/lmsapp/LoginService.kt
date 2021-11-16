package br.com.brunoloures.lmsapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.net.URL

object LoginService {

    val host = "https://wilamy.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getLogin(context: Context): List<Login> {
        var login = mutableListOf<Login>()

        val url = "$host/api/v2/usuarios/?format=json"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        login = parseJson<MutableList<Login>>(json)

        return login
    }
//
//    fun saveDisciplina (disciplina: Disciplina) {
//        val url = "$host/disciplinas"
//        var json = HttpHelper.post(url, GsonBuilder().create().toJson(disciplina))
//    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}