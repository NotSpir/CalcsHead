package com.example.calcshead

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calcshead.databinding.ActivityMainBinding
import java.lang.Exception

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    var wins = 0
    var total = 0

    fun onClickNext(view: View) {
        binding.txtAnswer.text?.clear()
        binding.txtTask.setBackgroundColor(Color.WHITE)
        val operandsA = arrayOf("*", "/", "-", "+")
        val operand = operandsA.random()
        var a = (10..99).random()
        var b = (10..99).random()
        if (operand == "/")
        {
            while(a % b != 0)
            {
                a = (10..99).random()
                b = (10..99).random()
            }
        }
        binding.txtTask.text = "${a} ${operand} ${b}"
        binding.txtAnswer.isEnabled = true
        binding.btnCheck.isEnabled = true
        binding.btnNext.isEnabled = false
    }

    fun onClickCheck(view: View) {
        try {
            val currentTask = binding.txtTask.text;
            val taskParts = currentTask.split(" ");
            val userNum = binding.txtAnswer.text.toString().toInt();
            var result = 0
            val operand = taskParts[1]
            val a = taskParts[0].toInt()
            val b = taskParts[2].toInt()
            when (operand){
                "+" -> result = a + b
                "-" -> result = a - b
                "*" -> result = a * b
                "/" -> result = a / b
            }

            if (result == userNum){
                wins++
                total++
                binding.txtTask.setBackgroundColor(Color.GREEN);
            }
            else{
                total++
                binding.txtTask.setBackgroundColor(Color.RED);
            }
            binding.txtWinRate.text = ("%.2f".format(((wins*1.00)/(total*1.00) * 100.00))).toString() + "%";
            binding.txtCorrect.text = wins.toString()
            binding.txtWrong.text = (total-wins).toString()
            binding.btnNext.isEnabled = true;
            binding.btnCheck.isEnabled = false;
            binding.txtAnswer.isEnabled = false;
        }
        catch (ex: Exception){

        }
    }
}