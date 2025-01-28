package com.polstat.sqlitepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var nim: EditText
    private lateinit var nama: EditText
    private lateinit var kelas: EditText
    private lateinit var nohp: EditText
    private lateinit var dbHandler: DBHandler
    private lateinit var addMhs: Button
    private lateinit var lihatMhs: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nim = findViewById(R.id.nimEditText)
        nama = findViewById(R.id.namaEditText)
        kelas = findViewById(R.id.kelasEditText)
        nohp = findViewById(R.id.nohpEditText)
        addMhs = findViewById(R.id.tambahButton)
        lihatMhs = findViewById(R.id.lihatButton)

        // creating a new dbhandler class and passing our context to it.
        dbHandler = DBHandler(this)

        addMhs.setOnClickListener {
            val nimmhs = nim.text.toString()
            val namamhs = nama.text.toString()
            val kelasmhs = kelas.text.toString()
            val nohpmhs = nohp.text.toString()

            if (nimmhs.isEmpty() || namamhs.isEmpty() || kelasmhs.isEmpty() || nohpmhs.isEmpty()) {
                Toast.makeText(this, "Lengkapilah semua data..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            dbHandler.addNewMahasiswa(nimmhs, namamhs, kelasmhs, nohpmhs)
            Toast.makeText(this, "Data Mahasiswa Telah Ditambahkan", Toast.LENGTH_SHORT).show()

            nim.setText("")
            nama.setText("")
            kelas.setText("")
            nohp.setText("")
        }
        lihatMhs.setOnClickListener {
            val intent = Intent(this, ViewMahasiswa::class.java)
            startActivity(intent)
            finish()

        }
    }
}
