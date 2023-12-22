package com.wresni.news.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wresni.news.data.model.SourceUiModel
import com.wresni.news.data.source.remote.ApiService
import com.wresni.news.data.source.remote.dto.SourceDto
import com.wresni.news.data.source.remote.paging.HeadlinePagingSource
import com.wresni.news.data.source.remote.paging.NewsPagingSource
import com.wresni.news.util.ApiResult
import com.wresni.news.util.Mapper
import com.wresni.news.util.handleApi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sourceMapper: Mapper<SourceDto, List<@JvmSuppressWildcards SourceUiModel>>
) : Repository {

    override fun getAllNews(keyword: String) = Pager(
        config = PagingConfig(pageSize = 50),
        pagingSourceFactory = {
            NewsPagingSource(apiService, keyword)
        }
    ).flow

    override fun getHeadlineNews() = Pager(
        config = PagingConfig(pageSize = 50),
        pagingSourceFactory = {
            HeadlinePagingSource(apiService)
        }
    ).flow

    override suspend fun getSources(): ApiResult<List<SourceUiModel>> {
        return handleApi(sourceMapper) {
            apiService.getSources()
        }
    }
}