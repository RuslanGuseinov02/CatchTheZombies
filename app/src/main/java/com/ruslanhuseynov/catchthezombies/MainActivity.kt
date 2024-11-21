package com.ruslanhuseynov.catchthezombies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ruslanhuseynov.catchthezombies.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imageView0.visibility = View.INVISIBLE
        binding.imageView1.visibility = View.INVISIBLE
        binding.imageView2.visibility = View.INVISIBLE
        binding.imageView3.visibility = View.INVISIBLE
        binding.imageView4.visibility = View.INVISIBLE
        binding.imageView5.visibility = View.INVISIBLE
        binding.imageView6.visibility = View.INVISIBLE
        binding.imageView7.visibility = View.INVISIBLE
        binding.imageView8.visibility = View.INVISIBLE

        val zombieViews = listOf(binding.imageView0,binding.imageView1,binding.imageView2,binding.imageView3
                ,binding.imageView4,binding.imageView5,binding.imageView6,binding.imageView7,binding.imageView8
        )

        binding.button.setOnClickListener {

            binding.imageViewMain.visibility = View.INVISIBLE
            binding.button.visibility = View.INVISIBLE

            val count = object : CountDownTimer(16000,1000){
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {

                    binding.timeView.text = "Time : ${millisUntilFinished / 1000}"

                    val randomIndex = Random.nextInt(zombieViews.size)
                    val randomImageView = zombieViews[randomIndex]
                    randomImageView.visibility = View.VISIBLE

                    randomImageView.postDelayed({randomImageView.visibility = View.INVISIBLE},500)
                }

                override fun onFinish() {

                    val intent = Intent(this@MainActivity,ResultActivity::class.java)
                    intent.putExtra("score",score)
                    finish()
                    startActivity(intent)
                }
            }
            count.start()
        }
    }
    @SuppressLint("SetTextI18n")
    fun imageClick(view: View) {

        score++
        binding.scoreView.text = "Score : $score"
    }
}