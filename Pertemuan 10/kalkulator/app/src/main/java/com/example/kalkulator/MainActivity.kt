package com.example.kalkulator

import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var form1: TextInputEditText
    private lateinit var form2: TextInputEditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi elemen UI
        form1 = findViewById(R.id.form1)
        form2 = findViewById(R.id.form2)
        button = findViewById(R.id.button)

        // Set listener untuk tombol Hitung
        button.setOnClickListener { calculate() }
    }

    private fun calculate() {
        val sideLengthText = form1.text.toString()
        val areaText = form2.text.toString()

        // hitung luas jika panjang sisi diisi
        if (sideLengthText.isNotEmpty() && areaText.isEmpty()) {
            val sideLength = sideLengthText.toDoubleOrNull()
            if (sideLength == null || sideLength <= 0) {
                form1.error = "Masukkan panjang sisi yang valid"
                return
            }
            val area = sideLength * sideLength
            form2.setText(area.toString())
            Toast.makeText(this, "Luas persegi dihitung!", Toast.LENGTH_SHORT).show()
        }
        // Hitung panjang sisi jika luas diisi
        else if (areaText.isNotEmpty() && sideLengthText.isEmpty()) {
            val area = areaText.toDoubleOrNull()
            if (area == null || area <= 0) {
                form2.error = "Masukkan luas persegi yang valid"
                return
            }
            val sideLength = sqrt(area)
            form1.setText(sideLength.toString())
            Toast.makeText(this, "Panjang sisi dihitung!", Toast.LENGTH_SHORT).show()
        }
        // Validasi jika kedua form diisi atau kosong
        else if (sideLengthText.isNotEmpty() && areaText.isNotEmpty()) {
            Toast.makeText(this, "Isi hanya salah satu form!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Isi salah satu form untuk menghitung!", Toast.LENGTH_SHORT).show()
        }
    }
}
