package com.example.mythappkt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var hackButton: Button
    private lateinit var mythButton: Button
    private lateinit var nextButton: Button

    private var currentIndex = 0
    private var score = 0

    private val questions = listOf(
        "Putting your phone in rice fixes water damage",
        "Airplane mode makes your phone charge faster",
        "Cracking your knuckles causes arthritis",
        "Cold showers improve alertness",
        "Charging overnight destroys your battery"
    )

    private val answers = listOf(
        false,
        true,
        false,
        true,
        false
    )

    private val explanations = listOf(
        "Wrong! Rice does not fix water damage.",
        "Correct! It reduces background activity.",
        "Correct! No scientific link to arthritis.",
        "Correct! Cold exposure increases alertness.",
        "Wrong! Modern phones stop charging at 100%."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)
        hackButton = findViewById(R.id.hackButton)
        mythButton = findViewById(R.id.mythButton)
        nextButton = findViewById(R.id.nextButton)

        showQuestion()

        hackButton.setOnClickListener {
            checkAnswer(true)
        }

        mythButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex++

            if (currentIndex < questions.size) {
                showQuestion()
                feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreScreen::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentIndex]
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentIndex]

        if (userAnswer == correctAnswer) {
            score++
            feedbackText.text = "Correct! ${explanations[currentIndex]}"
        } else {
            feedbackText.text = "Wrong! ${explanations[currentIndex]}"
        }
    }
}
