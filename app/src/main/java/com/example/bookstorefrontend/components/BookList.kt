package com.example.bookstorefrontend.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.bookstorefrontend.api.books.BookModel
import com.example.bookstorefrontend.api.details.DetailsViewModel
import com.example.bookstorefrontend.api.reviews.ReviewViewModel

/**
 * A composable function that displays a list of books in a grid format. Each book item can be clicked to open
 * a details and reviews sheet displaying additional information about the selected book.
 *
 * @param bookList A list of `BookModel` objects representing the books to be displayed in the grid.
 */

@Composable
fun BookList(bookList: List<BookModel>) {


    // ViewModel instances for retrieving book details and reviews
    val detailsViewModel: DetailsViewModel = viewModel()
    val reviewViewModel: ReviewViewModel = viewModel()

    // State to control the visibility of the details and reviews sheet
    var isSheetOpen by remember { mutableStateOf(false) }

    // State to keep track of the selected book's ID
    var selectedBookId by remember { mutableStateOf<Int?>(null) }

    // Fetches details and reviews when a book is selected
    LaunchedEffect(selectedBookId) {
        selectedBookId?.let {
            detailsViewModel.getDetailsById(it)
            reviewViewModel.getReviewsById(it)

        }
    }

    // Main container for the book list and details sheet
    Box(modifier = Modifier.fillMaxSize()) {

            // Displays the list of books in a grid with custom padding and spacing
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            )  {
                itemsIndexed(items = bookList){index, item ->
                    Book(
                        bookModel = item,
                        backgroundColor = MaterialTheme.colorScheme.tertiary ,
                        onClick = {
                            // Opens the details and reviews sheet and sets the selected book ID
                            isSheetOpen = true
                            selectedBookId = item.id
                        }
                    )
                }
            }
        }

    // Displays the details and reviews sheet when a book is selected
        DetailsAndReviews(
            isOpen = isSheetOpen,
            onClose = { isSheetOpen = false },
            details = detailsViewModel.detailsResponse,
            reviews = reviewViewModel.reviewResponse
        )
}


