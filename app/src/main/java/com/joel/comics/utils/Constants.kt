package com.joel.comics.utils

import com.joel.comics.domain.model.characters.allcharacters.CharacterThumbnail
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
import  com.joel.comics.domain.model.comics.allcomics.ComicThumbnail
import com.joel.comics.domain.model.comics.comicdetails.ComicDetailsThumbnail
import com.joel.comics.domain.model.comics.comicharacters.ComicCharactersThumbnail
import com.joel.comics.domain.model.series.allseries.SeriesThumbnail


fun getComicImageLink(thumbnail: ComicThumbnail):String{
    return thumbnail.path+ "." +thumbnail.extension
}

fun getComicDetailsImageLink(thumbnail: ComicDetailsThumbnail):String{
    return thumbnail.path+ "." +thumbnail.extension
}


fun getCharacterImageLink(thumbnail: CharacterThumbnail):String{
    return thumbnail.path+ "." +thumbnail.extension
}

fun getSeriesImageLink(thumbnail: SeriesThumbnail):String{
    return thumbnail.path+ "." +thumbnail.extension
}

//fun getComicDetailsImageLink(thumbnail: ComicCharactersThumbnail):String{
//    return thumbnail.path+ "." +thumbnail.extension
//}

const val SPLASH_SCREEN_DURATION = 2000L


class Constants {


    companion object {
        const val URL_TESTER = "https://gateway.marvel.com/v1/public/characters?ts=thesoer&apikey=001ac6c73378bbfff488a36141458af2&hash=72e5ed53d1398abb831c3ceec263f18b"
        const val imageUrl = "http://x.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/detail.jpg"

        const val MARVEL_API_KEY = "03b242b3d560dfcf16005cb557604912"
        private const val MARVEL_PRIVATE_KEY = "901daf251dcc851cc3e783894948eb69a0efedc3"

        const val MARVEL_BASE_URL = "https://gateway.marvel.com/v1/public/"
        const val CHARACTER_ENDPOINT = "characters"
        const val CHARACTER_ID_ENDPOINT = "characters/{characterId}"

        const val PAGE_SIZE = 50

        val ts = Timestamp(System.currentTimeMillis()).time.toString()

        fun hash(): String{
            val input = "$ts$MARVEL_PRIVATE_KEY$MARVEL_API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(12,'0')
        }
    }
}