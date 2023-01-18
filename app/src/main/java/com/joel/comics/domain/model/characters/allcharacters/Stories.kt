package com.joel.comics.domain.model.characters.allcharacters

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)