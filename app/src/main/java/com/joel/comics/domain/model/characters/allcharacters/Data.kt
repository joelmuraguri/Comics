package com.joel.comics.domain.model.characters.allcharacters

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<AllCharactersResult>,
    val total: Int
)