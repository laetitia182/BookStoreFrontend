package com.example.bookstorefrontend.api.books

import com.example.bookstorefrontend.api.RetrofitInstance
import retrofit2.http.GET

interface BookService {
    @GET("list/books")
    suspend fun getBooks(): List<BookModel>

    companion object{

        private var bookService: BookService? = null

        fun getInstance(): BookService {
            if (bookService == null) {
                bookService = RetrofitInstance.createService(BookService::class.java)
            }
            return bookService!!
        }
    }
}