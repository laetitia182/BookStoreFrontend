package com.example.bookstorefrontend.api.reviews

import com.example.bookstorefrontend.api.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {
    @GET("reviews/{id}")
    suspend fun getReviewsById(@Path("id") id: Int): List<ReviewModel>

    companion object {
        private var reviewService: ReviewService? = null

        fun getInstance(): ReviewService {
            if ( reviewService == null) {
                reviewService = RetrofitInstance.createService(ReviewService::class.java)
            }
            return reviewService!!
        }
    }
}