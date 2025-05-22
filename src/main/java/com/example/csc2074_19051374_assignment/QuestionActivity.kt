package com.example.csc2074_19051374_assignment

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import kotlin.Result


class QuestionActivity:AppCompatActivity(), View.OnClickListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var questiontext: TextView
    private lateinit var questionList: MutableList<Question>
    private var selectedOptionPosition = 0
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var checkButton: Button
    private lateinit var resetButton: Button
    private lateinit var previousButton: Button
    private var questionsCounter = 0
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private lateinit var name:String
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progressText)
        questiontext = findViewById(R.id.question_text)
        checkButton = findViewById(R.id.button_submit)
        resetButton = findViewById(R.id.reset_button)
        previousButton = findViewById(R.id.previous_button)

        option1 = findViewById(R.id.option_1)
        option2 = findViewById(R.id.option_2)

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        checkButton.setOnClickListener(this)
        resetButton.setOnClickListener{
            finish()
            startActivity(intent)
            showNextQuestion()
            Toast.makeText(this,"Current restart",Toast.LENGTH_LONG).show()
        }

        questionList = Constants.getQuestions()
        Log.d("Question Size", "${questionList.size}")

        showNextQuestion()
        if (intent.hasExtra(Constants.USER_NAME)){
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }


    private fun showNextQuestion() {


        if (questionsCounter < questionList.size) {
            checkButton.text = "Check"
            currentQuestion = questionList[questionsCounter]

            resetOptions()
            val question = questionList[questionsCounter]
            progressBar.progress = questionsCounter
            progressText.text = "${questionsCounter + 1}/${progressBar.max}"
            questiontext.text = question.question
            option1.text = question.option1
            option2.text = question.option2

        } else {
            checkButton.text = "Finish"
            Intent(this, Result::class.java).also {
                it.putExtra(Constants.USER_NAME,name)
                it.putExtra(Constants.SCORE,score)
                it.putExtra(Constants.TOTAL_QUESTIONS,questionList.size)
                startActivity(it)
            }
        }
        questionsCounter++
        answered = false

    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()

        options.add(option1)
        options.add(option2)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.question_option)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option_1 -> {
                selectedOption(option1, 1)
            }

            R.id.option_2 -> {
                selectedOption(option2, 2)
            }

            R.id.button_submit -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()
        selectedAnswer = selectedOptionNumber
        selectedOptionPosition = selectedOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_question_option
        )

    }

    private fun restartQuiz(){
        currentQuestion
        score = 0
        showNextQuestion()
        resetButton.isEnabled = false
    }

    private fun checkAnswer() {
        answered = true

        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
            highlightedAnswer(selectedAnswer)

        } else {
            when (selectedAnswer) {
                1 -> {
                    option1.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_question_option
                    )
                }

                2 -> {
                    option2.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_question_option
                    )

                }

            }
        }

        checkButton.text = "Next"
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer
        highlightedAnswer(selectedAnswer)
        when (selectedAnswer) {
            1 -> {
                option1.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_question_option
                )
            }

            2 -> {
                option2.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_question_option
                )
            }
        }

    }

    private fun highlightedAnswer(answer: Int) {
        when (answer) {
            1 -> {
                option1.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_question_option
                )
            }

            2 -> {
                option2.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_question_option
                )
            }
        }

        }
    }








