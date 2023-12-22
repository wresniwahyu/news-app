package com.wresni.news.screen.detail

import android.os.Bundle
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.wresni.news.data.source.remote.dto.Article
import com.wresni.news.ui.component.Toolbar
import com.wresni.news.util.convertUtcToGmtPlus7

@Composable
fun NewsDetailScreen(
    navController: NavController
) {
    val selectedArticle = navController.previousBackStackEntry?.savedStateHandle?.get<Bundle>("article")
    val article = selectedArticle?.getParcelable<Article>("article")

    Column {
        Toolbar("Detail") {
            navController.navigateUp()
        }
        Image(
            painter = rememberAsyncImagePainter(article?.urlToImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Text(
                text = article?.title.orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article?.author.orEmpty())
            Text(text = article?.publishedAt?.convertUtcToGmtPlus7().orEmpty())
        }

        Text(
            modifier = Modifier.padding(16.dp),
            text = article?.content.orEmpty()
        )
    }
}