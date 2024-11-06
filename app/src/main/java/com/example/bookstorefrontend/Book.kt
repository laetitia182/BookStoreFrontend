package com.example.bookstorefrontend

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


@Composable
fun Book(
    backgroundColor: Color,
    title: String,
    author: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor)
            .padding(16.dp)
            .size(width = 100.dp, height = 200.dp)
            .clickable { onClick() }
    ) {
        Column (
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = title,
                style = TextStyle(color = Color.White, fontSize = 24.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = author,
                style = TextStyle(color = Color.White, fontSize = 18.sp)
            )
        }
    }
}