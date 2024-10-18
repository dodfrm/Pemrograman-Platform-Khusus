package com.polstat.perpustakaan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book_loans")
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "loan_status", nullable = false)
    private String loanStatus;

    @Column(name = "late_days")
    private int lateDays;

    @PrePersist
    public void prePersist() {
        this.borrowDate = new Date(); // Tanggal sekarang
        this.loanStatus = "sedang dipinjam"; // Status default
    }
}