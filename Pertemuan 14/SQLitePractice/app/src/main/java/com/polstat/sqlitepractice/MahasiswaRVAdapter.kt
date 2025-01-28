package com.polstat.sqlitepractice

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MahasiswaRVAdapter(
    private val mahasiswaModalArrayList: ArrayList<MahasiswaModel>,
    private val context: Context
) : RecyclerView.Adapter<MahasiswaRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mahasiswa_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modal = mahasiswaModalArrayList[position]
        holder.nimTV.text = modal.nim
        holder.namaTV.text = modal.nama
        holder.kelasTV.text = modal.kelas
        holder.nohpTV.text = modal.nohp

        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateMahasiswaActivity::class.java)
            intent.putExtra("nim", modal.nim)
            intent.putExtra("nama", modal.nama)
            intent.putExtra("kelas", modal.kelas)
            intent.putExtra("nohp", modal.nohp)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = mahasiswaModalArrayList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nimTV: TextView = itemView.findViewById(R.id.nimTextView)
        val namaTV: TextView = itemView.findViewById(R.id.namaTextView)
        val kelasTV: TextView = itemView.findViewById(R.id.kelasTextView)
        val nohpTV: TextView = itemView.findViewById(R.id.nohpTextView)
    }
}

