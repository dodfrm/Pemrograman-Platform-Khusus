package com.polstat.perpustakaan.repository;

import com.polstat.perpustakaan.entity.BookLoan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bookLoan", path = "bookLoan")
public interface BookLoanRepository extends PagingAndSortingRepository<BookLoan, Long>, CrudRepository<BookLoan, Long> {

    // Menemukan peminjaman buku berdasarkan member ID
    List<BookLoan> findByMemberId(@Param("member_id") Long memberId);

    // Menemukan peminjaman buku berdasarkan book ID
    List<BookLoan> findByBookId(@Param("book_id") Long bookId);

    // Menemukan peminjaman berdasarkan status
    List<BookLoan> findByLoanStatus(@Param("status") String status);

    List<BookLoan> findByBookIdAndLoanStatus(@Param("book_id") Long bookId, @Param("loan_status") String loanStatus);
}