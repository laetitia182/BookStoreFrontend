package com.example.bookstorefrontend.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstorefrontend.api.reviews.ReviewModel


/**
 * A composable function that displays a single book review, including the reviewer's name, rating, and review text.
 *
 * @param reviewModel An instance of `ReviewModel` containing the review's details, such as reviewer name, star rating,
 *                    and review text.
 */
@Composable
fun Review( reviewModel: ReviewModel) {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Row {
                Text(
                    text = reviewModel.reviewer,
                    style = TextStyle(fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "(${reviewModel.stars} / 5)",
                    style = TextStyle(fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                )
            }
            Text(
                text = reviewModel.text,
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )

        }
    }
}