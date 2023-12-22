package com.wresni.news.data.mapper

import com.wresni.news.data.model.SourceUiModel
import com.wresni.news.data.source.remote.dto.SourceDto
import com.wresni.news.util.Mapper
import javax.inject.Inject

class SourceMapper @Inject constructor(): Mapper<SourceDto, List<@JvmSuppressWildcards SourceUiModel>> {
    override fun map(input: SourceDto): List<SourceUiModel> {
        return input.sources?.map {
            SourceUiModel(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                url = it.url.orEmpty(),
                category = it.category.orEmpty(),
                country = it.country.orEmpty(),
                description = it.description.orEmpty(),
                language = it.language.orEmpty()
            )
        }.orEmpty()
    }
}