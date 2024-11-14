package com.example.bookstorefrontend.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstorefrontend.api.details.DetailsModel


/**
 * A composable function that displays detailed information about a book, such as the author, year, type, publisher,
 * language, and ISBN.
 *
 * @param detailsModel An instance of `DetailsModel` containing the book's details to display.
 */
@Composable
fun Details( detailsModel: DetailsModel) {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(

        ) {
            Text(
                text = "Author: ${detailsModel.author}",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            Text(
                text = "Year: ${detailsModel.year}",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            Text(
                text = "Type: ${detailsModel.type}",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            Text(
                text = "Publisher: ${detailsModel.publisher}",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            Text(
                text = "Language: ${detailsModel.language}",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            Text(
                text = "ISBN: ${detailsModel.isbn}",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
        }
    }
}