package com.joel.comics.model.marvelmodel.marvdata.marvresponse.character

data class CharacterInfo(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: DataX,
    val etag: String,
    val status: String
)