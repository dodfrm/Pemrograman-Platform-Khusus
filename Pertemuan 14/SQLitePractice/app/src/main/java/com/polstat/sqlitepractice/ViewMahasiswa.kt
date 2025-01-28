package com.polstat.sqlitepractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewMahasiswa : AppCompatActivity() {

    private lateinit var mahasiswaModalArrayList: ArrayList<MahasiswaModel>
    private lateinit var dbHandler: DBHandler
    private lateinit var mahasiswaRVAdapter: MahasiswaRVAdapter
    private lateinit var mahasiswaRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_mahasiswa)

        mahasiswaModalArrayList = ArrayList()
        dbHandler = DBHandler(this)

        // Read data from the database
        mahasiswaModalArrayList = dbHandler.readMahasiswa()

        // Set up RecyclerView and Adapter
        mahasiswaRVAdapter = MahasiswaRVAdapter(mahasiswaModalArrayList, this)
        mahasiswaRV = findViewById(R.id.idRVMahasiswa)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mahasiswaRV.layoutManager = linearLayoutManager

        mahasiswaRV.adapter = mahasiswaRVAdapter
    }
}