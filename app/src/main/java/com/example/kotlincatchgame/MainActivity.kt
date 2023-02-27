package com.example.kotlincatchgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.Random


class MainActivity : AppCompatActivity() {

    var score=0
    private lateinit var scoreText: TextView
    private lateinit var timeText:TextView
    var imageArray=ArrayList<ImageView>()
    var handler=Handler( )
    var runnable= Runnable {  }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreText=findViewById<TextView>(R.id.scoreText)
        timeText=findViewById<TextView>(R.id.timeText)
        val image1=findViewById<ImageView>(R.id.imageView1)
        val image2=findViewById<ImageView>(R.id.imageView2)
        val image3=findViewById<ImageView>(R.id.imageView3)
        val image4=findViewById<ImageView>(R.id.imageView4)
        val image5=findViewById<ImageView>(R.id.imageView5)
        val image6=findViewById<ImageView>(R.id.imageView6)
        val image7=findViewById<ImageView>(R.id.imageView7)
        val image8=findViewById<ImageView>(R.id.imageView8)
        val image9=findViewById<ImageView>(R.id.imageView9)
        //ImageArray
        imageArray.add(image1)
        imageArray.add(image2)
        imageArray.add(image3)
        imageArray.add(image4)
        imageArray.add(image5)
        imageArray.add(image6)
        imageArray.add(image7)
        imageArray.add(image8)
        imageArray.add(image9)

        hideImages()
        //countDown Timer
        object :CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text="Time:"+millisUntilFinished/1000
            }

            override fun onFinish() {

                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                timeText.text="Time:0"
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes"){dialog,which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()

            }

        }.start()

    }


    fun hideImages(){

        runnable=object :Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                val random=Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,750)
            }

        }
        handler.post(runnable)
    }


    fun increaseScore(view: View){

        score++
        scoreText.text="Score: ${score.toString()}"
    }
}