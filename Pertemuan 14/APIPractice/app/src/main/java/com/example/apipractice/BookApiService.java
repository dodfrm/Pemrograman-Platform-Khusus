package com.example.apipractice;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface BookApiService {
    @GET("/books")
    Call<List<Book>> getBooks();
    @GET("/books/{id}")
    Call<Book> getBookById(@Path("id") Long id);

    @POST("/books")
    Call<Book> createBook(@Body Book book);

    @PUT("/books/{id}")
    Call<Book> updateBook(@Path("id") Long id, @Body Book book);

    @DELETE("/books/{id}")
    Call<Void> deleteBook(@Path("id") Long id);

    @GET("/books/search")
    Call<List<Book>> searchBooks(@Query("keyword") String keyword);
}