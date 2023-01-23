package com.joel.comics.domain.model.comics.comicharacters

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicCharactersResult>,
    val total: Int
)