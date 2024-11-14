package com.example.bookstorefrontend.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A singleton object that provides a Retrofit instance configured with a base URL and Gson converter for making HTTP requests.
 * This instance lazily initializes the Retrofit object and includes a method to create API service interfaces.
 */
object RetrofitInstance {
    // The base URL for the API, using localhost (10.0.2.2) for Android emulator access
    private const val BASE_URL = "http://10.0.2.2/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Sets the base URL for the HTTP requests
            .addConverterFactory(GsonConverterFactory.create()) // Adds a converter factory for JSON deserialization
            .build()
    }

    /**
     * Creates a Retrofit service for a given API interface.
     *
     * @param serviceClass The interface class defining the API endpoints.
     * @return An implementation of the API interface defined by `serviceClass`, ready for network calls.
     */
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}