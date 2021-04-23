package br.com.alessandro.marvelchallenge.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataResponse(
    @SerializedName("data")
    val data: CharactersResponse
) : Serializable

data class CharactersResponse(
    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<Character>
) : Serializable

data class Character(
    @SerializedName( "id")
    val id: Int,

    @SerializedName( "thumbnail")
    val thumbnail: Thumbnail,

    @SerializedName( "name")
    val name: String,

    @SerializedName( "description")
    val description: String,

    @SerializedName(  "modified")
    val modified: String
): Serializable

data class Thumbnail(
    @SerializedName( "path")
    val path: String,

    @SerializedName( "extension")
    val extension: String
): Serializable {
    fun getUrl() = "$path.$extension".replace("http://", "https://") /* todo, validar se preciso*/
}
