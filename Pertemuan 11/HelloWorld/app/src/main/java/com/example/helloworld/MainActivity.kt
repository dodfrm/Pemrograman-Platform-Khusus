package com.example.helloworld

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
    }

    fun btnClick(view: View?) {
        val tlp = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel:081234567899")
        )
        startActivity(tlp)
        Toast.makeText(
            this, "you have Pressed : " +
                    btn1.text, Toast.LENGTH_LONG
        ).show()
    }

    fun btn2Click(view: View?) {
        val setting = Intent(
            Settings.ACTION_SETTINGS
        )
        startActivity(setting)
        Toast.makeText(
            this, "you have Pressed : " +
                    btn2.text, Toast.LENGTH_LONG
        ).show()
    }

    fun btn3Click(view: View?){
        val myIntent = Intent()
        myIntent.setType("audio/mp3")
        myIntent.setAction(Intent.ACTION_GET_CONTENT)
        startActivity(myIntent)
    }
}