package com.wresni.news.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.wresni.news.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String = "",
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackPressed.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Go Back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Purple40)
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    Toolbar("Detail Page")
}