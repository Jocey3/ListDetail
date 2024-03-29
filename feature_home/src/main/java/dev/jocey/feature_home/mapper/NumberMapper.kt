package dev.jocey.feature_home.mapper

import dev.jocey.domain.model.NumberDomain
import dev.jocey.feature_home.model.NumberView
import javax.inject.Inject

class NumberMapper @Inject constructor() {
    fun mapFromDomainToView(numberDomain: NumberDomain): NumberView {
        return NumberView(number = numberDomain.number, text = numberDomain.text)
    }

}