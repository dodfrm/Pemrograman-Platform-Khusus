package com.polstat.perpustakaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.polstat.perpustakaan.repository.BookRepository;
import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.gen.AddBookRequest;
import com.polstat.perpustakaan.gen.AddBookResponse;
import com.polstat.perpustakaan.gen.BookType;
import com.polstat.perpustakaan.gen.GetAllBookRequest;
import com.polstat.perpustakaan.gen.GetAllBookResponse;

import java.util.List;

@Endpoint
public class BookEndpoint {

    private static final String NAMESPACE_URI = "http://polstat.com/perpustakaan/gen";

    @Autowired
    private BookRepository bookRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddBookRequest")
    @ResponsePayload
    public AddBookResponse addBook(@RequestPayload AddBookRequest request) {
        // Create a new Book entity and save it to the database
        Book newBook = new Book(null, request.getTitle(), request.getAuthor(), request.getDescription());
        bookRepository.save(newBook);

        // Prepare the response with the new book's ID
        AddBookResponse response = new AddBookResponse();
        response.setId(newBook.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllBookRequest")
    @ResponsePayload
    public GetAllBookResponse getAllBooks(@RequestPayload GetAllBookRequest request) {
        // Fetch all books from the database
        List<Book> books = bookRepository.findAll();

        // Prepare the response
        GetAllBookResponse response = new GetAllBookResponse();
        for (Book book : books) {
            // Create and populate BookType for each book
            BookType bookType = new BookType();
            bookType.setId(book.getId());
            bookType.setTitle(book.getTitle());
            bookType.setAuthor(book.getAuthor());
            bookType.setDescription(book.getDescription());

            // Add each book to the response's book list
            response.getBookList().add(bookType);
        }
        return response;
    }
}
