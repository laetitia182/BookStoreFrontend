package com.example.bookstorefrontend.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstorefrontend.api.details.DetailsModel
import com.example.bookstorefrontend.api.reviews.ReviewModel
import kotlinx.coroutines.launch


/**
 * A composable function that displays a modal bottom sheet with detailed information about a book and user reviews.
 * The sheet can be toggled open or closed and contains sections for book details and user reviews.
 *
 * @param isOpen Boolean flag that controls whether the sheet is open or closed.
 * @param onClose Lambda function that handles the action when the sheet is dismissed.
 * @param details A list of `DetailsModel` objects containing the book's detailed information.
 * @param reviews A list of `ReviewModel` objects containing user reviews for the book.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAndReviews(
    isOpen: Boolean,
    onClose: () -> Unit,
    details: List<DetailsModel>,
    reviews: List<ReviewModel>
) {
    // Coroutine scope for managing the state of the modal bottom sheet
    val scope = rememberCoroutineScope()

    // State for the modal bottom sheet, configured to skip the partially expanded state
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    // Conditionally display the modal bottom sheet when isOpen is true
    if (isOpen) {
        ModalBottomSheet(
            onDismissRequest = {
                onClose()
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "About the Book",
                    style = TextStyle(fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 14.dp)
                )
                // LazyColumn for displaying book details
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(items = details){index, item ->
                        Details(
                            detailsModel = item
                        )
                    }
                }
                // Divider between the details and reviews sections
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                // LazyColumn for displaying user reviews
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(items = reviews){index, item ->
                        Review(
                            reviewModel = item
                        )
                    }
                }
            }
        }
        // Launches the coroutine to open the modal bottom sheet when isOpen becomes true
        LaunchedEffect(isOpen) {
            scope.launch {
                sheetState.show()
            }
        }
    }
}