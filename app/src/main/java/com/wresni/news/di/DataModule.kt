package com.wresni.news.di

import com.wresni.news.data.Repository
import com.wresni.news.data.RepositoryImpl
import com.wresni.news.data.mapper.SourceMapper
import com.wresni.news.data.model.SourceUiModel
import com.wresni.news.data.source.remote.dto.SourceDto
import com.wresni.news.util.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideRepository(
        impl: RepositoryImpl
    ): Repository

    @Binds
    abstract fun provideSourceMapper(
        mapper: SourceMapper
    ): Mapper<SourceDto, List<@JvmSuppressWildcards SourceUiModel>>

}