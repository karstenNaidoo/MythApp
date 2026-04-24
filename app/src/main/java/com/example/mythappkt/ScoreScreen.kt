package com.example.mythappkt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreScreen : AppCompatActivity() {

    private lateinit var scoreText: TextView
    private lateinit var messageText: TextView
    private lateinit var reviewButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        scoreText = findViewById(R.id.scoreText)
        messageText = findViewById(R.id.messageText)
        reviewButton = findViewById(R.id.reviewButton)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        scoreText.text = "Score: $score / $total"
        messageText.text = "" 

        reviewButton.setOnClickListener {
            val resultMessage = when {
                score == total -> "Master Hacker! "
                score >= total / 2 -> "Good job! You're getting there."
                else -> "Stay safe online Keep learning."
            }
            messageText.text = resultMessage
        }
    }
}
