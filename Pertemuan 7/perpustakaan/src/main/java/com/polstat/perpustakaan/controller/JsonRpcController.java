package com.polstat.perpustakaan.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.polstat.perpustakaan.dto.BookDto;
import com.polstat.perpustakaan.rpc.JsonRpcRequest;
import com.polstat.perpustakaan.rpc.JsonRpcResponse;
import com.polstat.perpustakaan.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonRpcController {
    @Autowired
    private BookService bookService;

    @Operation(summary = "Handles JSON-RPC requests for book operations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JsonRpcResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request or operation failed", content = @Content)
    })
    @PostMapping("/jsonrpc")
    public ResponseEntity<Object> handleJsonRpcRequest(@RequestBody JsonRpcRequest request) {
        try {
            String method = request.getMethod();
            JsonNode params = request.getParams();
            System.out.println("Method: " + method);
            switch (method) {
                case "createBook":
                    String title = params.get("title").asText();
                    String author = params.get("author").asText();
                    String description = params.get("description").asText();
                    BookDto book = BookDto.builder()
                            .title(title)
                            .description(description)
                            .author(author)
                            .build();
                    bookService.createBook(book);
                    return ResponseEntity.ok(new JsonRpcResponse("created", request.getId()));

                case "getBooks":
                    List<BookDto> books = bookService.getBooks();
                    return ResponseEntity.ok(new JsonRpcResponse(books, request.getId()));

                case "searchBooks":
                    String keyword = params.get("keyword").asText();
                    List<BookDto> foundBooks = bookService.searchBooks(keyword);
                    return ResponseEntity.ok(new JsonRpcResponse(foundBooks, request.getId()));

                default:
                    return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}