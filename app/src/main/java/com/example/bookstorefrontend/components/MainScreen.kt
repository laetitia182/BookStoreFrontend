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

@Composable
fun MainScreen(
    modifier: Modifier
) {
    val bookViewModel: BookViewModel = viewModel()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        bookViewModel.getBookList()
    }

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
                var isChecked by remember { mutableStateOf(false) }
                var showDialog by remember { mutableStateOf(false) }
                var permissionRequested by remember { mutableStateOf(false) }
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
            Box(modifier = Modifier.fillMaxSize()) {
                BookList(bookList = bookViewModel.bookListResponse)
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
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