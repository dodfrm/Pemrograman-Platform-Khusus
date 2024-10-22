package com.polstat.perpustakaan.handler;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.entity.BookLoan;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.repository.BookLoanRepository;
import com.polstat.perpustakaan.repository.BookRepository;
import com.polstat.perpustakaan.repository.MemberRepository;

@Component
@RepositoryEventHandler(BookLoan.class)
public class BookLoanEventHandler {
    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Handler sebelum membuat peminjaman buku baru
    @HandleBeforeCreate
    public void handleBeforeCreate(BookLoan bookLoan) throws Exception {
        if (bookLoan.getMember() == null || bookLoan.getMember().getId() == null) {
            throw new IllegalArgumentException("Member tidak ditemukan");
        }

        if (bookLoan.getBook() == null || bookLoan.getBook().getId() == null) {
            throw new IllegalArgumentException("Buku tidak ditemukan");
        }

        Optional<Member> member = memberRepository.findById(bookLoan.getMember().getId());
        Optional<Book> book = bookRepository.findById(bookLoan.getBook().getId());

        if (!member.isPresent()) {
            throw new IllegalArgumentException("Member tidak ditemukan");
        }
        if (!book.isPresent()) {
            throw new IllegalArgumentException("Buku tidak ditemukan");
        }

        // Pastikan buku belum dipinjam
        List<BookLoan> existingLoans = bookLoanRepository.findByBookIdAndLoanStatus(bookLoan.getBook().getId(),
                "sedang dipinjam");
        if (!existingLoans.isEmpty()) {
            throw new IllegalArgumentException("Buku sedang dipinjam");
        }

        // Set informasi peminjaman
        bookLoan.setBorrowDate(new Date());
        bookLoan.setLoanStatus("sedang dipinjam");
    }

    public void setDefaultValuesForBookLoan(BookLoan bookLoan) {
        // Atur borrowDate menjadi tanggal sekarang
        if (bookLoan.getBorrowDate() == null) {
            bookLoan.setBorrowDate(new Date());
        }

        // Atur loanStatus menjadi "sedang dipinjam" jika belum diisi
        if (bookLoan.getLoanStatus() == null || bookLoan.getLoanStatus().isEmpty()) {
            bookLoan.setLoanStatus("sedang dipinjam");
        }
    }

    // Handler sebelum memperbarui peminjaman (misalnya saat buku dikembalikan)
    @HandleBeforeSave
    public void handleBeforeSave(BookLoan bookLoan) throws Exception {
        // Jika status peminjaman berubah menjadi "sudah dikembalikan", hitung
        // keterlambatan
        if ("sudah dikembalikan".equals(bookLoan.getLoanStatus())) {
            // Set tanggal kembali
            bookLoan.setReturnDate(new Date());

            // Hitung jumlah hari telat
            long daysLate = calculateLateDays(bookLoan.getBorrowDate(), bookLoan.getReturnDate());
            bookLoan.setLateDays((int) daysLate);
        }
    }

    // Fungsi untuk menghitung jumlah hari telat pengembalian
    private long calculateLateDays(Date borrowDate, Date returnDate) {
        long diffInMillies = returnDate.getTime() - borrowDate.getTime();
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
        int maxLoanDays = 7; // Jumlah hari maksimal peminjaman (misalnya 7 hari)
        return Math.max(0, diffInDays - maxLoanDays);
    }
}