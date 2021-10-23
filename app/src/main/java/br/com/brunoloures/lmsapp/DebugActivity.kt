package br.com.brunoloures.lmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class DebugActivity : AppCompatActivity() {

    private val className: String
        get(){
            val s = javaClass.name
            return s.substring(s.lastIndexOf("."))
        }

    private val TAG = "LMSApp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"${className}.${className}.OnCreate chamado")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "${className}.onStart chamado")
    }


    override fun onRestart(){
        super.onRestart()
        Log.d(TAG,"${className}.OnRestart chamado" )
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG,"${className}.onResume chamado" )
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG,"${className}.onStop chamado" )
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"${className}.onDestroy chamado" )
    }

}