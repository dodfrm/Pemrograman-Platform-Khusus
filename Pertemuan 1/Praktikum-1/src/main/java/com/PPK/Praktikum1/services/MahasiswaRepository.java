package com.PPK.Praktikum1.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PPK.Praktikum1.models.Mahasiswa;

public interface MahasiswaRepository extends JpaRepository <Mahasiswa, String>{
     @Query("SELECT m FROM Mahasiswa m WHERE m.nim LIKE %:keyword% OR m.nama LIKE %:keyword%")
    public List<Mahasiswa> searchByNimOrNama(@Param("keyword") String keyword);
}
