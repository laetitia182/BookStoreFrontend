package com.example.bookstorefrontend.api.reviews

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing and providing book review data.
 * It fetches reviews for a book by interacting with the `ReviewService`.
 */
class ReviewViewModel: ViewModel() {

    // The list of reviews retrieved from the API, initially an empty list
    var reviewResponse: List<ReviewModel> by mutableStateOf(listOf())

    // A string to store any error message encountered during the API call
    var errorMessage: String by mutableStateOf("")

    /**
     * Fetches reviews for the book with the specified `id` from the backend service.
     * The function uses the `ReviewService` to make a network call and retrieve reviews asynchronously.
     * It updates the `reviewResponse` state with the fetched reviews or the `errorMessage` state if an error occurs.
     *
     * @param id The unique identifier of the book for which reviews need to be fetched.
     */
    fun getReviewsById(id: Int) {
        // Launching a coroutine in the ViewModel scope
        viewModelScope.launch {
            // Get the instance of ReviewService to make the API call
            val reviewService = ReviewService.getInstance()
            try {
                // Attempt to fetch reviews for the book with the given ID
                val reviews = reviewService.getReviewsById(id)
                // Update the reviewResponse state with the fetched reviews
                reviewResponse = reviews
            } catch (e:Exception){
                // If an error occurs during the network call, set the errorMessage state
                errorMessage = e.message.toString()
            }
        }
    }
}