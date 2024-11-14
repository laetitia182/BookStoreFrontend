package com.example.bookstorefrontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstorefrontend.api.books.BookModel

/**
 * A composable function that displays a book item with a title and author within a styled, clickable box.
 *
 * @param bookModel A data model representing the book to display, which includes the title and author.
 * @param backgroundColor Background color for the box containing the book's details.
 * @param onClick Lambda function that defines the action when the book item is clicked.
 */

@Composable
fun Book(
    bookModel: BookModel,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(16.dp)
            .size(width = 100.dp, height = 200.dp)
            .clickable { onClick() }
    ) {
        Column (
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,

        ){
            Text(
                text = bookModel.title,
                style = TextStyle(color = Color.White, fontSize = 18.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = bookModel.author,
                style = TextStyle(color = Color.White, fontSize = 14.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}