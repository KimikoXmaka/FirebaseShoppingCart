package com.example.rectrun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
    }

    fun Restart(view: View) {
        val back = Intent(this, MainActivity::class.java)
        startActivity(back)
    }
}