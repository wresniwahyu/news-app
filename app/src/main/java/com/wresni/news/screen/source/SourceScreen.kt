package com.wresni.news.screen.source

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wresni.news.ui.component.LoadingState
import com.wresni.news.ui.component.SourceItem
import com.wresni.news.ui.component.SourceItemData
import com.wresni.news.util.showToast
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SourceScreen(
    viewModel: SourceViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is SourceViewModel.Event.ShowMessage -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    if (state.isLoading) {
        LoadingState()
    } else {
        LazyColumn {
            items(state.sources) {
                SourceItem(
                    article = SourceItemData(
                        title = it.name,
                        description = it.description,
                        url = it.url
                    )
                )
            }
        }
    }
}