package com.joel.comics.domain.model.comics.comicdetails

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicDetailsResult>,
    val total: Int
)