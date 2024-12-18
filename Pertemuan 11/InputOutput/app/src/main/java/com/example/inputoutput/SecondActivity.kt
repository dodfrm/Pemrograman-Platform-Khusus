package com.example.inputoutput
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class SecondActivity : AppCompatActivity() {
    private lateinit var nameTxt: TextView
    private lateinit var sureNameTxt: TextView
    private lateinit var ageTxt: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameTxt = findViewById(R.id.name)
        sureNameTxt = findViewById(R.id.sureName)
        ageTxt = findViewById(R.id.age)

        val userName = intent.getStringExtra("USER_NAME")
        val userSureName = intent.getStringExtra("USER_SURENAME")
        val userAge = intent.getStringExtra("USER_AGE")

        nameTxt.text = "Name : $userName"
        sureNameTxt.text = "Sure Name : $userSureName"
        ageTxt.text = "Age : $userAge"
    }
}