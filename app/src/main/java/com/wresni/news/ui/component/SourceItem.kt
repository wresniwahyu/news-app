package com.wresni.news.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SourceItem(
    modifier: Modifier = Modifier,
    article: SourceItemData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = article.description,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.url)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(1.dp)
                .background(Color.LightGray)
        )
    }
}

data class SourceItemData(
    val title: String,
    val description: String,
    val url: String,
)

@Preview(showBackground = true)
@Composable
fun SourceItemPrev() {
    SourceItem(
        article = SourceItemData(
            "Title",
            "Lorem ipsum dolor sit amet orem ipsum dolor sit amet orem ipsum dolor sit amet orem ipsum dolor sit amet orem ipsum dolor sit amet ",
            "www.harianjogja.com",
        )
    )
}