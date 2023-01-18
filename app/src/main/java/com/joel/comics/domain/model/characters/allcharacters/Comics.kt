package com.joel.comics.domain.model.characters.allcharacters

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)