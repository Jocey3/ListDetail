package dev.jocey.data.model.remote

data class NumberApiModel(
    val number: Int,
    val text: String,
    val found: Boolean,
    val type: String
)
