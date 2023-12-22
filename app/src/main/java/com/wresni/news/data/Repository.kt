package com.wresni.news.data

import androidx.paging.PagingData
import com.wresni.news.data.model.SourceUiModel
import com.wresni.news.data.source.remote.dto.Article
import com.wresni.news.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllNews(keyword: String): Flow<PagingData<Article>>
    fun getHeadlineNews(): Flow<PagingData<Article>>
    suspend fun getSources(): ApiResult<List<SourceUiModel>>
}