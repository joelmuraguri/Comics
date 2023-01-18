package com.joel.comics.domain.model.characters.allcharacters

data class AllCharactersResult(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: CharacterThumbnail,
    val urls: List<Url>
)