package br.com.alessandro.marvelchallenge.data

import br.com.alessandro.marvelchallenge.data.model.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/public/characters")
   suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : DataResponse
}
