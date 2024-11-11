package com.example.bookstorefrontend.api.books

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel: ViewModel(){
    var bookListResponse:List<BookModel> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getBookList() {
        viewModelScope.launch {
            val bookService = BookService.getInstance()
            try {
                val bookList = bookService.getBooks()
                bookListResponse = bookList
            }
            catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}