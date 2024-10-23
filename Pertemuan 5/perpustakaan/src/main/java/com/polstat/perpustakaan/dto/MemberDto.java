package com.polstat.perpustakaan.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    @NotEmpty(message = "ID Anggota wajib diisi.")
    private String memberID;
    @NotEmpty(message = "Nama Anggota wajib diisi.")
    private String name;
    @NotEmpty(message = "Alamat Anggota wajib diisi.")
    private String address;
    @NotEmpty(message = "Nomor Telepon Anggota wajib diisi.")
    private String phoneNumber;
}
