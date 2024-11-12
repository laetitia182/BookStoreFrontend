package com.example.bookstorefrontend.api.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {
    var detailsResponse: List<DetailsModel> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getDetailsById(id: Int) {
        viewModelScope.launch {
            val detailsService = DetailsService.getInstance()
            try {
                val details = detailsService.getDetailsById(id)
                detailsResponse = details
            } catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}