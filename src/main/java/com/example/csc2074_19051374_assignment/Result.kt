package com.example.csc2074_19051374_assignment

import android.content.Intent
import android.os.Bundle
import android.text.LoginFilter.UsernameFilterGMail
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score

class Result : AppCompatActivity() {

    private lateinit var textViewName:  TextView
    private lateinit var textViewScore: TextView
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textViewName = findViewById(R.id.tv_name)
        textViewScore = findViewById(R.id.tv_score)
        finishButton = findViewById(R.id.btn_finish)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val score = intent.getIntExtra(Constants.SCORE,0)
        val name = intent.getStringExtra(Constants.USER_NAME)

        textViewScore.text = "Your score is $score out of $totalQuestions"
        textViewName.text = name


        finishButton.setOnClickListener{
            Intent(this,MainActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}