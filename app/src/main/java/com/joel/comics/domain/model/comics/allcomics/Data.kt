package com.joel.comics.domain.model.comics.allcomics

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<AllComicsResult>,
    val total: Int
)