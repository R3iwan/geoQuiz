package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_england, true),
        Question(R.string.question_kazakhstan, true),
        Question(R.string.question_denmark, false),
        Question(R.string.question_spain, false),
        Question(R.string.question_usa, false)
    )

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var resultsButton: Button

    var totalScore = 0
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
        resultsButton = findViewById(R.id.results_button)

        trueButton.setOnClickListener {
            if (!isQuestionAnswered) {
                checkAnswer(true, sumOfExam)
                isQuestionAnswered = true
                isPrevAnsw = true
            }
        }
        falseButton.setOnClickListener {
            if (!isQuestionAnswered) {
                checkAnswer(false, sumOfExam)
                isQuestionAnswered = true
                isPrevAnsw = true
            }
        }

        nextButton.setOnClickListener {
            if (currentIndex < questionBank.size) {
                currentIndex++
                updateQuestion()
            }
        }

        prevButton.setOnClickListener {
            if (currentIndex > 0) {
                if (!isPrevAnsw) {
                    currentIndex--
                    updateQuestion()
                }
            }
        }

        updateQuestion()

        val (value1, value2) = checkAnswer(true, totalScore)

        resultsButton.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("totalScore", value2)
            this.startActivity(intent)

        }
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean, sumOfExam: Int): Pair<Boolean, Int> {
        val correctAnswer = questionBank[currentIndex].answer

        if (userAnswer == correctAnswer) {
            this.sumOfExam += 20
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
        }
        totalScore = sumOfExam
        return Pair(userAnswer, this.totalScore)
    }
}




