package com.PPK.Praktikum1.models;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "mahasiswa")
public class Mahasiswa {
    
    @Id
    private String nim;

    private String nama;
    private Date tanggal_lahir;
    private String jurusan;

    public String getJurusan() {
        return jurusan;
    }
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }
    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
}
