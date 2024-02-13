package dev.jocey.data.mapper

import dev.jocey.data.model.local.NumberEntity
import dev.jocey.data.model.remote.NumberApiModel
import dev.jocey.domain.model.NumberDomain
import javax.inject.Inject

class NumberMapper @Inject constructor() {
    fun mapFromApiToDomain(apiModel: NumberApiModel): NumberDomain {
        return NumberDomain(number = apiModel.number, text = apiModel.text)
    }

    fun mapFromApiToEntity(apiModel: NumberApiModel): NumberEntity {
        return NumberEntity(
            number = apiModel.number,
            text = apiModel.text,
            found = apiModel.found,
            type = apiModel.type
        )
    }
}