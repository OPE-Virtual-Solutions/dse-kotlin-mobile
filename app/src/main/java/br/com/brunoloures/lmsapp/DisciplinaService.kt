package br.com.brunoloures.lmsapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.Exception


object DisciplinaService {

    val host = "https://wilamy.pythonanywhere.com/"
    val TAG = "WS_LMSApp"

    fun getDisciplinas (context: Context): List<Disciplina> {
        var disciplinas = ArrayList<Disciplina>()
        if (isOnline(context) == true) {
            val url = "$host/api/v2/pedidos/?format=json"
            val json = HttpHelper.get(url)
            disciplinas = parseJson(json)
            for (d in disciplinas) {
                saveOffline(d)
            }
            Log.d(TAG, "deu certo com internet")
            return disciplinas
        } else {
            val dao = DatabaseManager.getDisciplinaDAO()
            val disciplinas = dao.findAll()
            Log.d(TAG, "deu certo sem internet")
            return disciplinas
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
    fun saveOffline(disciplina: Disciplina) : Boolean {
        val dao = DatabaseManager.getDisciplinaDAO()
        if (! existeDisciplina(disciplina)) {
            dao.insert(disciplina)
        }
        return true
    }

    fun existeDisciplina(disciplina: Disciplina): Boolean {
        val dao = DatabaseManager.getDisciplinaDAO()
        return disciplina.id?.let { dao.getById(it) } != null

    }

    fun saveDisciplina(disciplina: Disciplina) {
        val url = "$host/api/v2/pedidos/?format=json"
        var json = HttpHelper.post(url, GsonBuilder().create().toJson(disciplina))
    }

    inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson<T>(json, type)
    }
}