package com.example.bookstorefrontend.api.details

import com.example.bookstorefrontend.api.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET("details/{id}")
    suspend fun getDetailsById(@Path("id") id: Int): List<DetailsModel>

    companion object{

        private var detailsService: DetailsService? = null

        fun getInstance(): DetailsService {
            if (detailsService == null){
                detailsService = RetrofitInstance.createService(DetailsService::class.java)
            }
            return detailsService!!
        }
    }
}