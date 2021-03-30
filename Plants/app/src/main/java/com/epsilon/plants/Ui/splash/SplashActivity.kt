package com.epsilon.plants.Ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.epsilon.plants.R
import com.epsilon.plants.Ui.signin.SignInActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        val intent = Intent(this, SignInActivity::class.java)
        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 3000)
    }
}