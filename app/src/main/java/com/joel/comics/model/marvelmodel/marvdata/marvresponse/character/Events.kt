package com.joel.comics.model.marvelmodel.marvdata.marvresponse.character

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)