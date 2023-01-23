package com.joel.comics.domain.model.comics.comicharacters

data class ComicCharactersResult(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: ComicCharactersThumbnail,
    val urls: List<Url>
)