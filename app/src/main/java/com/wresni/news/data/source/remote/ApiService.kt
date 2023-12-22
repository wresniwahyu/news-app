package com.wresni.news.data.source.remote

import com.wresni.news.BuildConfig
import com.wresni.news.data.source.remote.dto.NewsDto
import com.wresni.news.data.source.remote.dto.SourceDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val PARAM_API_KEY = "apiKey"
        private const val PARAM_PAGE = "page"
        private const val PARAM_PAGE_SIZE = "pageSize"
        private const val PARAM_KEYWORD = "q"
    }

    @GET("everything")
    suspend fun getAllNews(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(PARAM_PAGE) page: Int,
        @Query(PARAM_KEYWORD) q: String,
        @Query(PARAM_PAGE_SIZE) pageSize: Int = 50,
    ): Response<NewsDto>

    @GET("top-headlines")
    suspend fun getHeadlineNews(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(PARAM_PAGE) page: Int,
        @Query(PARAM_KEYWORD) q: String = "general",
        @Query(PARAM_PAGE_SIZE) pageSize: Int = 50,
    ): Response<NewsDto>

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
    ): Response<SourceDto>

}