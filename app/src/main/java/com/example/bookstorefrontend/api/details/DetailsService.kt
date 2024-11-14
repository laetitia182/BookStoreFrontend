package com.example.bookstorefrontend.api.details

import com.example.bookstorefrontend.api.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service interface for accessing detailed book information from the API.
 * Defines the method to retrieve detailed information about a book by its ID.
 */
interface DetailsService {
    /**
     * Sends a GET request to retrieve detailed information about a book using its unique ID.
     * The function is `suspend` to support Kotlin coroutines for asynchronous network calls.
     *
     * @param id The unique identifier of the book whose details are to be fetched.
     * @return A list of `DetailsModel` objects containing detailed information about the book.
     */
    @GET("details/{id}")
    suspend fun getDetailsById(@Path("id") id: Int): List<DetailsModel>

    companion object{

        private var detailsService: DetailsService? = null

        /**
         * Retrieves a singleton instance of `DetailsService`.
         * If the instance is not already created, it initializes it using `RetrofitInstance`.
         *
         * @return The singleton instance of `DetailsService` for making API requests.
         */
        fun getInstance(): DetailsService {
            if (detailsService == null){
                detailsService = RetrofitInstance.createService(DetailsService::class.java)
            }
            return detailsService!!
        }
    }
}