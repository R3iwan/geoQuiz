package com.example.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class ResultsActivity : ComponentActivity(){
    private var newTotalScore = 0
    private lateinit var showResultsButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        showResultsButton = findViewById(R.id.show_results_button)

        val mainActivity = MainActivity()
        newTotalScore = mainActivity.totalScore

        showResultsButton.setOnClickListener{
            val toast = Toast.makeText(this, "Your Score is $newTotalScore", Toast.LENGTH_LONG).show()
        }

    }
}