package com.example.csc2074_19051374_assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName: EditText = findViewById(R.id.input)
        val btnStart: Button = findViewById(R.id.button_start)
        btnStart.setOnClickListener {
            if (etName.text.isNotEmpty()) {
                val intent = Intent(this@MainActivity, QuestionActivity::class.java).also {
                    intent.putExtra(Constants.USER_NAME, etName.text.toString())
                    intent.putExtra(Constants.SCORE, 5)
                    intent.putExtra(Constants.TOTAL_QUESTIONS, 5)
                    startActivity(it)
                    finish()
                }
            } else {
                Toast.makeText(this, "Please Enter Your Name",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}