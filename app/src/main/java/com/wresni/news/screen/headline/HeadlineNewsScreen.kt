package com.wresni.news.screen.headline

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.wresni.news.Screens
import com.wresni.news.ui.component.ArticleItem
import com.wresni.news.ui.component.ArticleItemData
import com.wresni.news.ui.component.LoadingState
import com.wresni.news.util.convertUtcToGmtPlus7

@Composable
fun HeadlineNewsScreen(
    navController: NavController,
    viewModel: HeadlineNewsViewModel = hiltViewModel()
) {
    val news = viewModel.getHeadlines().collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {

        val listState = rememberLazyListState()
        val isIniatialLoading by remember {
            derivedStateOf {
                listState.layoutInfo.viewportSize == IntSize.Zero
            }
        }

        if (isIniatialLoading) {
            LoadingState()
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            state = listState
        ) {
            items(news.itemCount) { index ->
                val item = news[index]
                ArticleItem(
                    modifier = Modifier.clickable {
                        val bundle = Bundle().apply { putParcelable("article", item) }
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "article",
                            bundle
                        )

                        navController.navigate(Screens.Detail.route)
                    },
                    article = ArticleItemData(
                        title = item?.title.orEmpty(),
                        description = item?.description.orEmpty(),
                        image = item?.urlToImage.orEmpty(),
                        time = item?.publishedAt?.convertUtcToGmtPlus7().orEmpty()
                    )
                )
            }
        }

    }
}