package com.example.bookstorefrontend.api.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the UI-related data and business logic related to book details.
 * This ViewModel interacts with the `DetailsService` to fetch the detailed information of a book
 * based on its unique ID and handles the results or errors. It provides the UI with the book's details or
 * an error message when the API call fails.
 */
class DetailsViewModel: ViewModel() {

    // The list of book details retrieved from the API, initially an empty list
    var detailsResponse: List<DetailsModel> by mutableStateOf(listOf())

    // A string to store any error message encountered during the API call
    var errorMessage: String by mutableStateOf("")

    /**
     * Fetches the details of a book by its ID from the API using the `DetailsService` and updates the `detailsResponse`.
     * If the API call fails, the `errorMessage` state is updated with the exception message.
     *
     * @param id The unique identifier of the book whose details are to be fetched.
     */
    fun getDetailsById(id: Int) {
        // Launching a coroutine in the ViewModel scope
        viewModelScope.launch {
            // Get the instance of DetailsService to make the API call
            val detailsService = DetailsService.getInstance()
            try {
                // Attempt to fetch the details of the book using the provided ID
                val details = detailsService.getDetailsById(id)
                // Update the `detailsResponse` with the retrieved details of the book
                detailsResponse = details
            } catch (e:Exception){
                // If the request fails, update the `errorMessage` with the error details
                errorMessage = e.message.toString()
            }
        }
    }
}