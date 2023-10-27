package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.time.ZoneOffset

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    private var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_england, true),
        Question(R.string.question_kazakhstan, true),
        Question(R.string.question_denmark, false),
        Question(R.string.question_spain, false),
        Question(R.string.question_usa, false))

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var cheatButton: Button

    private var totalScore = 0
    private var sumOfExam = 0
    private var isQuestionAnswered = false
    private var isPrevAnsw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)

        trueButton.setOnClickListener {
            if(!isQuestionAnswered){
                checkAnswer(true)
                isQuestionAnswered = true
                isPrevAnsw = true
            }
        }
        falseButton.setOnClickListener{
            if(!isQuestionAnswered){
                checkAnswer(false)
                isQuestionAnswered = true
                isPrevAnsw = true
            }
        }

        nextButton.setOnClickListener{
            if(currentIndex < questionBank.size){
                currentIndex++
            updateQuestion()
            }
        }

        prevButton.setOnClickListener{
            if (currentIndex > 0){
                if (!isPrevAnsw){
                    currentIndex--
                    updateQuestion()
                }
            }
        }

        cheatButton.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            startActivity(intent)
        }

        updateQuestion()

        }
        private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        }

        private fun checkAnswer(userAnswer: Boolean): Boolean {
            val correctAnswer = questionBank[currentIndex].answer

            if (userAnswer == correctAnswer){
                R.string.correct_toast
                sumOfExam += 20
            } else {
                R.string.incorrect_toast
            }
            totalScore = sumOfExam
            return userAnswer
        }
}



