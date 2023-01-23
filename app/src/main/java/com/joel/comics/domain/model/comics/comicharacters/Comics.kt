package com.joel.comics.domain.model.comics.comicharacters

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)