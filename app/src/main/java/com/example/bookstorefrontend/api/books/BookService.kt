package com.example.bookstorefrontend.api.books

import com.example.bookstorefrontend.api.RetrofitInstance
import retrofit2.http.GET

/**
 * Retrofit service interface for accessing book-related API endpoints.
 * Provides methods to retrieve book data from a backend.
 */
interface BookService {
    /**
     * Sends a GET request to retrieve a list of books.
     * The function is `suspend` to support Kotlin coroutines, allowing asynchronous network calls.
     *
     * @return A list of `BookModel` objects representing the books retrieved from the API.
     */
    @GET("list/books") // when using the sample solution backend the path needs to be changed to ("/books")
    suspend fun getBooks(): List<BookModel>

    companion object{

        private var bookService: BookService? = null

        /**
        * Retrieves a singleton instance of `BookService`.
        * If the instance is not already initialized, it uses `RetrofitInstance` to create it.
        *
        * @return A singleton instance of `BookService` for making API requests.
        */
        fun getInstance(): BookService {
            if (bookService == null) {
                bookService = RetrofitInstance.createService(BookService::class.java)
            }
            return bookService!!
        }
    }
}