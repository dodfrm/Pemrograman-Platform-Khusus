package com.polstat.recyclerviewpractice
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

class MahasiswaAdapter(
    private val dataList: ArrayList<Mahasiswa>,
    private val onItemClick: (Mahasiswa) -> Unit // Menambahkan lambda untuk aksi klik
) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_mahasiswa, parent, false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = dataList[position]
        holder.txtNama.text = mahasiswa.nama
        holder.txtNpm.text = mahasiswa.nim
        holder.txtNoHp.text = mahasiswa.nohp

        // Menambahkan listener klik
        holder.itemView.setOnClickListener {
            onItemClick(mahasiswa) // Memanggil lambda dengan objek Mahasiswa
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNama: TextView = itemView.findViewById(R.id.txt_nama_mahasiswa)
        val txtNpm: TextView = itemView.findViewById(R.id.txt_nim_mahasiswa)
        val txtNoHp: TextView = itemView.findViewById(R.id.txt_nohp_mahasiswa)
    }
}

