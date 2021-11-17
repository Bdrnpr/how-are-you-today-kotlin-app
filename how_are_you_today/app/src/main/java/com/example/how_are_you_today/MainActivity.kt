package com.example.how_are_you_today

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    // declaring variables
    lateinit var sharedPreferences : SharedPreferences
    var counterRed : Int = 0
    var counterYellow: Int = 0
    var counterGreen : Int = 0
    var greenstats : String? = null
    var yellowstats : String? = null
    var redstats : String? = null




    override fun onCreate(savedInstanceState: Bundle?)
        {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            sharedPreferences = this.getSharedPreferences("com.example.how_are_you_today",Context.MODE_PRIVATE)

            greenstats = sharedPreferences.getString("GreenFace",null)
            yellowstats = sharedPreferences.getString("YellowFace",null)
            redstats = sharedPreferences.getString("RedFace",null)

            if (greenstats == null) {
                sharedPreferences.edit().putString("GreenFace", 0.toString()).apply()
                sharedPreferences.edit().putString("YellowFace", 0.toString()).apply()
                sharedPreferences.edit().putString("RedFace", 0.toString()).apply()
            }

            ReadStats()
            WriteStats()

             counterRed = redstats!!.toInt()
            counterGreen = greenstats!!.toInt()
            counterYellow = yellowstats!!.toInt()
            ControlBackgroung()
            val image_red = findViewById(R.id.image_red_sad_face) as ImageView
            val image_yellow = findViewById(R.id.image_yellow_neutral_face) as ImageView
            val image_green = findViewById(R.id.image_green_smile_face) as ImageView


            image_red.setOnClickListener {
                counterRed++
                TV_redstats.text = counterRed.toString()
                ControlBackgroung()
                SaveFace()
            }
            image_yellow.setOnClickListener {
                    counterYellow++
                    TV_yellowstats.text = counterYellow.toString()
                    ControlBackgroung()
                    SaveFace()
                }
            image_green.setOnClickListener {
                    counterGreen++
                    TV_greenStats.text = counterGreen.toString()
                    ControlBackgroung()
                    SaveFace()
                }

        }

    private fun WriteStats() {
        TV_greenStats.text = greenstats
        TV_yellowstats.text = yellowstats
        TV_redstats.text = redstats    }


    fun ControlBackgroung(){
        if (counterRed > counterGreen && counterRed > counterYellow)
            Main.setBackgroundResource(R.drawable.red_background2)
        else if (counterGreen > counterRed && counterGreen > counterYellow)
                Main.setBackgroundResource(R.drawable.green_background2)
        else if (counterYellow > counterRed && counterYellow > counterGreen)
            Main.setBackgroundResource(R.drawable.yellow_background2)
        else
            Main.setBackgroundColor(Color.WHITE)
    }
    fun ReadStats(){
        greenstats = sharedPreferences.getString("GreenFace","")
        yellowstats = sharedPreferences.getString("YellowFace","")
        redstats = sharedPreferences.getString("RedFace","")
    }
    fun SaveFace(){
        var green1 = TV_greenStats.text
        var yellow1 = TV_yellowstats.text
        var red1= TV_redstats.text
        sharedPreferences.edit().putString("GreenFace", green1.toString()).apply()
        sharedPreferences.edit().putString("YellowFace",yellow1.toString()).apply()
        sharedPreferences.edit().putString("RedFace",red1.toString()).apply()
    }
   }