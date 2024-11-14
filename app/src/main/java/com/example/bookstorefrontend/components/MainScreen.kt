package com.example.bookstorefrontend.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookstorefrontend.api.books.BookViewModel
import kotlinx.coroutines.launch


/**
 * A composable function that displays the main screen of the application, with a modal navigation drawer, a settings
 * floating action button, and a list of books fetched from a ViewModel. Includes a snackbar notification and a dialog
 * for requesting notification permissions.
 *
 * @param modifier The `Modifier` instance used to modify the layout or behavior of this composable.
 */
@Composable
fun MainScreen(
    modifier: Modifier
) {
    // ViewModel for managing book data
    val bookViewModel: BookViewModel = viewModel()

    // Drawer state to control the opening and closing of the navigation drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Coroutine scope to handle asynchronous tasks
    val scope = rememberCoroutineScope()

    // State for managing snackbar notifications
    val snackbarHostState = remember { SnackbarHostState() }

    // Launch effect to fetch the book list when this composable is initialized
    LaunchedEffect(Unit) {
        bookViewModel.getBookList()
    }

    // Modal navigation drawer that includes settings and notifications options
    ModalNavigationDrawer (
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Settings",
                    modifier = Modifier.padding(32.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                // State for managing notification checkbox and dialog
                var isChecked by remember { mutableStateOf(false) }
                var showDialog by remember { mutableStateOf(false) }
                var permissionRequested by remember { mutableStateOf(false) }

                // Navigation item for toggling notifications with a checkbox
                NavigationDrawerItem(
                    label = {
                        Row{
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { checked ->
                                    isChecked = checked
                                    if(checked && !permissionRequested) {
                                        showDialog = true
                                        permissionRequested = true
                                    }
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = "Notifications",
                                modifier = Modifier.padding(12.dp),
                                fontSize = 20.sp
                            )
                        }
                         },
                    selected = false,
                    onClick = {}
                )
                // Display dialog to confirm enabling notifications if checkbox is checked
                if(showDialog) {
                    AlertDialog(
                        onDismissRequest = {showDialog = false},
                        title = {Text(text = "Allow BookStore to send you notifications?")},
                        confirmButton = {
                            TextButton(onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "This is a notification!",
                                    )
                                }
                                showDialog = false
                            }) {
                                Text("Allow")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showDialog = false
                                permissionRequested = false
                            }) {
                                Text("Deny")
                            }
                        }
                    )
                }
            }
        }
    ) {
            // Main content layout
            Box(modifier = Modifier.fillMaxSize()) {
                BookList(bookList = bookViewModel.bookListResponse)
                // Snackbar host for displaying messages
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
                // Floating action button to open and close the drawer
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(36.dp),
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    // Icon and label for the settings button
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.padding(end = 4.dp)) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint = Color.White,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Text(
                            text = "Settings",
                            style = TextStyle(color = Color.White, fontSize = 18.sp),
                            modifier = Modifier.padding(end = 12.dp)
                        )
                    }
                }
            }
    }
}