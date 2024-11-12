package com.example.bookstorefrontend.api.reviews

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReviewViewModel: ViewModel() {
    var reviewResponse: List<ReviewModel> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getReviewsById(id: Int) {
        viewModelScope.launch {
            val reviewService = ReviewService.getInstance()
            try {
                val reviews = reviewService.getReviewsById(id)
                reviewResponse = reviews
            } catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}