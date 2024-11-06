package com.example.bookstorefrontend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookList(modifier: Modifier = Modifier) {
    val books = List(4) {index ->  "Book $index"}
    var isSheetOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        )  {
            items(books) {
                Book(
                    backgroundColor = MaterialTheme.colorScheme.tertiary ,
                    title = "Book",
                    author = "Author",
                    onClick = {isSheetOpen = true}
                )
            }
        }
        DetailsAndReviews(
            isOpen = isSheetOpen,
            onClose = {isSheetOpen = false}
        )

    }

}


