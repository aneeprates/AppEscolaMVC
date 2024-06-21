package com.example.appescolamvc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appescolamvc.MainActivity
import com.example.appescolamvc.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val splashTime: Long = 2000 //Variável que fala o tempo que a tela vai ficar aberta no aplicativo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        //Gerando um atraso com Coroutine
        CoroutineScope(Dispatchers.Main).launch {
            delay(splashTime)

            //Ao finalizar o Splash ir para página:
            val intent = Intent(this@SplashActivity,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*//Splash - primeira forma
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)*/


    }
}