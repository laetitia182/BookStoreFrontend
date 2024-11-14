package com.example.bookstorefrontend.api.reviews

import com.example.bookstorefrontend.api.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service interface for accessing book reviews from the API.
 * Defines the method to retrieve reviews of a book based on its unique ID.
 */
interface ReviewService {
    /**
     * Sends a GET request to retrieve reviews for a specific book using its unique ID.
     * The function is `suspend` to support Kotlin coroutines for asynchronous network calls.
     *
     * @param id The unique identifier of the book whose reviews are to be fetched.
     * @return A list of `ReviewModel` objects containing the reviews for the book.
     */
    @GET("reviews/{id}")
    suspend fun getReviewsById(@Path("id") id: Int): List<ReviewModel>

    companion object {
        private var reviewService: ReviewService? = null

        /**
         * Retrieves a singleton instance of `ReviewService`.
         * If the instance is not already created, it initializes it using `RetrofitInstance`.
         *
         * @return The singleton instance of `ReviewService` for making API requests.
         */
        fun getInstance(): ReviewService {
            if ( reviewService == null) {
                reviewService = RetrofitInstance.createService(ReviewService::class.java)
            }
            return reviewService!!
        }
    }
}