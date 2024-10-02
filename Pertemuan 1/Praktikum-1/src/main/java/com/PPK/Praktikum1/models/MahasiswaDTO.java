package com.PPK.Praktikum1.models;
import java.sql.Date;
import jakarta.validation.constraints.*;

public class MahasiswaDTO {
    @NotEmpty(message = "NIM tidak boleh kosong")
    @Size(min = 9, max = 9, message = "NIM harus terdiri dari 9 karakter")
    @Pattern(regexp = "\\d{9}", message = "NIM hanya boleh terdiri dari angka")
    private String nim;

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String nama;

    @NotEmpty(message = "Jurusan tidak boleh kosong")
    private String jurusan;

    @Past(message = "Tanggal lahir tidak valid")
    private Date tanggal_lahir;

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

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
}
