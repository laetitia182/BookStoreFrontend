package com.example.bookstorefrontend.api.books

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BookService {
    @GET("books")
    suspend fun getBooks(): List<BookModel>

    companion object{
        var bookService: BookService? = null
        fun getInstance() : BookService {
            if (bookService == null){
                bookService = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/list/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BookService::class.java)
            }
            return bookService!!
        }
    }
}