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


@Composable
fun BookList(modifier: Modifier = Modifier, bookList: List<BookModel>) {


    val detailsViewModel: DetailsViewModel = viewModel()
    val reviewViewModel: ReviewViewModel = viewModel()
    var isSheetOpen by remember { mutableStateOf(false) }
    var selectedBookId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(selectedBookId) {
        selectedBookId?.let {
            detailsViewModel.getDetailsById(it)
            reviewViewModel.getReviewsById(it)

        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

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
                            isSheetOpen = true
                            selectedBookId = item.id
                        }
                    )
                }
            }
        }

        DetailsAndReviews(
            isOpen = isSheetOpen,
            onClose = { isSheetOpen = false },
            details = detailsViewModel.detailsResponse,
            reviews = reviewViewModel.reviewResponse
        )
}


