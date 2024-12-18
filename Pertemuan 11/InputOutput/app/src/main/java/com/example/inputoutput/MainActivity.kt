package com.example.inputoutput

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.inputoutput.R.id.button
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var name:TextInputEditText
    private lateinit var sureName:TextInputEditText
    private lateinit var age:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn = findViewById(button)
        name = findViewById(R.id.editText1)
        sureName = findViewById(R.id.editText2)
        age = findViewById(R.id.editText3)
    }

    fun btnClick(view: View?) {
        val userName = name.text.toString()
        val userSureName = sureName.text.toString()
        val userAge = age.text.toString()

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("USER_NAME", userName)
        intent.putExtra("USER_SURENAME", userSureName)
        intent.putExtra("USER_AGE", userAge)
        startActivity(intent)
    }
}