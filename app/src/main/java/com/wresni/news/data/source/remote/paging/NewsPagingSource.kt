package com.wresni.news.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wresni.news.data.source.remote.ApiService
import com.wresni.news.data.source.remote.dto.Article

class NewsPagingSource(
    private val apiService: ApiService,
    private val keyword: String
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getAllNews(page = page, q = keyword)

            if (response.isSuccessful) {
                val plants = response.body()?.articles.orEmpty()

                LoadResult.Page(
                    data = plants,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (plants.isEmpty()) null else page.plus(1),
                )
            } else {
                LoadResult.Error(Exception("ApiError"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}