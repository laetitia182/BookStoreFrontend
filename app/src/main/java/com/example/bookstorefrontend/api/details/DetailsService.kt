package com.example.bookstorefrontend.api.details

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET("details/{id}")
    suspend fun getDetailsById(@Path("id") id: Int): DetailsModel

    companion object{
        var detailsService: DetailsService? = null
        fun getInstance(): DetailsService {
            if (detailsService == null){
                detailsService = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DetailsService::class.java)
            }
            return detailsService!!
        }
    }
}