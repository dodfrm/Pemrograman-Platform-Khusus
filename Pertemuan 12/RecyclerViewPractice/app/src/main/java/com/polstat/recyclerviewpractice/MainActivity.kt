package com.polstat.recyclerviewpractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MahasiswaAdapter
    private lateinit var mahasiswaArrayList: ArrayList<Mahasiswa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addData()

        recyclerView = findViewById(R.id.recycler_view)
        adapter = MahasiswaAdapter(mahasiswaArrayList) { mahasiswa ->
            // Menampilkan Toast dengan nama mahasiswa
            Toast.makeText(this, "Hi ${mahasiswa.nama}!", Toast.LENGTH_SHORT).show()
        }
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun addData() {
        mahasiswaArrayList = arrayListOf(
            Mahasiswa("Dimas Maulana", "1414370309", "123456789"),
            Mahasiswa("Fadly Yonk", "1214234560", "987654321"),
            Mahasiswa("Ariyandi Nugraha", "1214230345", "987648765"),
            Mahasiswa("Aham Siswana", "1214378098", "098758124"),
            Mahasiswa("Rahadi Jalu", "235121344", "0812345355"),
            Mahasiswa("Muhammad Luqman", "123445560", "099877384857"),
            Mahasiswa("Isfan Fauzi", "7879230345", "548586723"),
            Mahasiswa("Geri Yesa", "00904378098", "928986872")
        )
    }
}

