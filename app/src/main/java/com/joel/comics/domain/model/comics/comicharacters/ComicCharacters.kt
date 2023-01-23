package com.joel.comics.domain.model.comics.comicharacters

data class ComicCharacters(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)
