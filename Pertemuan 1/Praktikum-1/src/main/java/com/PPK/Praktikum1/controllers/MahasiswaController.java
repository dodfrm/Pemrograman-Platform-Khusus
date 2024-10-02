package com.PPK.Praktikum1.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PPK.Praktikum1.models.Mahasiswa;
import com.PPK.Praktikum1.models.MahasiswaDTO;
import com.PPK.Praktikum1.services.MahasiswaRepository;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    @Autowired
    MahasiswaRepository mahasiswaRepository;

    private static final Logger logger = LoggerFactory.getLogger(MahasiswaController.class);
    // menampilkan data mahasiswa
    @GetMapping({"", "/"})
    public String showMahasiswaList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Mahasiswa> mahasiswa;
        
        if (keyword != null && !keyword.isEmpty()) {
            mahasiswa = mahasiswaRepository.searchByNimOrNama(keyword);
        } else {
            mahasiswa = mahasiswaRepository.findAll();
        }
        
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("keyword", keyword);
        model.addAttribute("mahasiswaDTO", new MahasiswaDTO());
        
        return "mahasiswa";
    }

    //menyimpan data mahasiswa
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

        try {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNim(mahasiswaDTO.getNim());
            mahasiswa.setNama(mahasiswaDTO.getNama());
            mahasiswa.setJurusan(mahasiswaDTO.getJurusan());
            mahasiswa.setTanggal_lahir(mahasiswaDTO.getTanggal_lahir());

            mahasiswaRepository.save(mahasiswa);
            redirectAttributes.addFlashAttribute("successMessage", "Data mahasiswa berhasil disimpan.");
        } catch (Exception e) {
            logger.error("Error saat menyimpan mahasiswa", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat menyimpan data.");
        }

        return "redirect:/mahasiswa";
    }

    //menghapus data mahasiswa
    @PostMapping("/delete")
    public String deleteMahasiswa(@RequestParam("nim") String nim, RedirectAttributes redirectAttributes) {
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findById(nim).orElseThrow(() ->
                new NoSuchElementException("Mahasiswa dengan NIM " + nim + " tidak ditemukan.")
            );
            mahasiswaRepository.delete(mahasiswa);
            redirectAttributes.addFlashAttribute("successMessage", "Data mahasiswa berhasil dihapus.");
        } catch (NoSuchElementException e) {
            logger.error("Mahasiswa dengan NIM {} tidak ditemukan untuk dihapus.", nim);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            logger.error("Error saat menghapus mahasiswa", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat menghapus data.");
        }

        return "redirect:/mahasiswa"; 
    }

    //Update data mahasiswa
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
            return "mahasiswa";
        }

        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findById(mahasiswaDTO.getNim())
                    .orElseThrow(() -> new NoSuchElementException("Mahasiswa dengan NIM " + mahasiswaDTO.getNim() + " tidak ditemukan."));
            mahasiswa.setNama(mahasiswaDTO.getNama());
            mahasiswa.setJurusan(mahasiswaDTO.getJurusan());
            mahasiswa.setTanggal_lahir(mahasiswaDTO.getTanggal_lahir());

            mahasiswaRepository.save(mahasiswa);
            redirectAttributes.addFlashAttribute("successMessage", "Data mahasiswa berhasil diperbarui.");
        } catch (NoSuchElementException e) {
            logger.error("Mahasiswa dengan NIM {} tidak ditemukan untuk diperbarui.", mahasiswaDTO.getNim());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            logger.error("Error saat memperbarui mahasiswa", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat memperbarui data.");
        }

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

    //Exception Handler
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RedirectAttributes redirectAttributes) {
        logger.error("Exception caught: ", ex);
        redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan: " + ex.getMessage());
        return "redirect:/mahasiswa";
    }
}
