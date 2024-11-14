package com.example.bookstorefrontend.api.books

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the UI-related data and business logic related to books.
 * This ViewModel interacts with the `BookService` to fetch a list of books and handles the results or errors.
 * It provides the UI with a list of books or an error message when the API call fails.
 */
class BookViewModel: ViewModel(){
    // The list of books retrieved from the API, initially an empty list
    var bookListResponse:List<BookModel> by mutableStateOf(listOf())

    // A string to store any error message encountered during the API call
    var errorMessage: String by mutableStateOf("")

    /**
     * Fetches the list of books from the API using the `BookService` and updates the `bookListResponse`.
     * If the API call fails, the `errorMessage` state is updated with the exception message.
     */
    fun getBookList() {
        // Launching a coroutine in the ViewModel scope
        viewModelScope.launch {
            // Get the instance of BookService to make API calls
            val bookService = BookService.getInstance()
            try {
                // Attempt to fetch the list of books from the API
                val bookList = bookService.getBooks()
                // Update the `bookListResponse` with the retrieved list of books
                bookListResponse = bookList
            }
            catch (e:Exception){
                // In case of error, update the `errorMessage` with the error details
                errorMessage = e.message.toString()
            }
        }
    }
}