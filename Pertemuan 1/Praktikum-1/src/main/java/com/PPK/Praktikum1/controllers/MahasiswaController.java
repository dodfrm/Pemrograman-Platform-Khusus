package com.PPK.Praktikum1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PPK.Praktikum1.models.Mahasiswa;
import com.PPK.Praktikum1.models.MahasiswaDTO;
import com.PPK.Praktikum1.services.MahasiswaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    @Autowired
    MahasiswaRepository mahasiswaRepository;

    @GetMapping({"", "/"})
    public String showMahasiswaList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
    List<Mahasiswa> mahasiswa;
    
    if (keyword != null && !keyword.isEmpty()) {
        // Search by NIM or Nama
        mahasiswa = mahasiswaRepository.searchByNimOrNama(keyword);
    } else {
        // Jika tidak ada keyword tampilkan seluruh mahasiswa
        mahasiswa = mahasiswaRepository.findAll();
    }
    
    model.addAttribute("mahasiswa", mahasiswa);
    model.addAttribute("keyword", keyword);
    MahasiswaDTO mahasiswaDTO = new MahasiswaDTO();
    model.addAttribute("mahasiswaDTO", mahasiswaDTO);
    
    return "mahasiswa";
}

    // Menambah Data Mahasiswa
   @PostMapping("/save")
    public String saveMahasiswa(
            @Valid @ModelAttribute MahasiswaDTO mahasiswaDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<Mahasiswa> mahasiswa = mahasiswaRepository.findAll();
            model.addAttribute("mahasiswa", mahasiswa);
            model.addAttribute("mahasiswaDTO", mahasiswaDTO);
            model.addAttribute("errorMessage", "Validasi gagal, mohon periksa kembali input Anda.");
            return "mahasiswa";
        }

        // Simpan mahasiswa jika tidak ada error
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(mahasiswaDTO.getNim());
        mahasiswa.setNama(mahasiswaDTO.getNama());
        mahasiswa.setJurusan(mahasiswaDTO.getJurusan());
        mahasiswa.setTanggal_lahir(mahasiswaDTO.getTanggal_lahir());

        mahasiswaRepository.save(mahasiswa);

        // Tambahkan pesan sukses ke RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Data mahasiswa berhasil disimpan.");
        return "redirect:/mahasiswa"; // Redirect setelah data berhasil disimpan
    }

    // Menghapus data mahasiswa
    @PostMapping("/delete")
    public String deleteMahasiswa(@RequestParam("nim") String nim, RedirectAttributes redirectAttributes) {
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findById(nim).get();
            mahasiswaRepository.delete(mahasiswa);
            redirectAttributes.addFlashAttribute("successMessage", "Data mahasiswa berhasil dihapus.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat menghapus data.");
        }
        return "redirect:/mahasiswa"; 
    }

    // Memperbaharui data mahasiswa
    @PostMapping("/update")
    public String updateMahasiswa(
            @Valid @ModelAttribute("mahasiswaDTO") MahasiswaDTO mahasiswaDTO, 
            BindingResult bindingResult, 
            Model model, 
            RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            List<Mahasiswa> mahasiswaList = mahasiswaRepository.findAll();
            model.addAttribute("mahasiswa", mahasiswaList);
            model.addAttribute("mahasiswaDTO", mahasiswaDTO);
            model.addAttribute("errorMessage", "Validasi gagal, mohon periksa kembali input Anda.");
            return "mahasiswa"; // Kembali ke halaman jika ada error
        }

        // Simpan perubahan jika tidak ada error
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(mahasiswaDTO.getNim());
        mahasiswa.setNama(mahasiswaDTO.getNama());
        mahasiswa.setJurusan(mahasiswaDTO.getJurusan());
        mahasiswa.setTanggal_lahir(mahasiswaDTO.getTanggal_lahir());

        mahasiswaRepository.save(mahasiswa);

        redirectAttributes.addFlashAttribute("successMessage", "Data mahasiswa berhasil diperbarui.");
        return "redirect:/mahasiswa";
    }

    // Menampilkan detail mahasiswa
    @RequestMapping("/detail")
    public String showDetailMahasiswa(@RequestParam("nim") String nim, Model model) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(nim).orElse(null);
        if (mahasiswa != null) {
            model.addAttribute("mahasiswa", mahasiswa);
            return "detail";  // Returns the detail view page
        } else {
            return "redirect:/mahasiswa";  // Redirect to the list if mahasiswa not found
        }
    }

}