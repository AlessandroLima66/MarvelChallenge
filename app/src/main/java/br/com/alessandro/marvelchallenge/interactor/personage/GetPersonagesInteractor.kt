package br.com.alessandro.marvelchallenge.interactor.personage

import br.com.alessandro.marvelchallenge.data.ApiService
import br.com.alessandro.marvelchallenge.data.model.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPersonagesInteractor(private val apiService: ApiService) {

    suspend fun execute(offset: Int = 0, limit: Int): DataResponse {
        return withContext(Dispatchers.IO) {
            apiService.getCharacters(offset, limit)
        }
    }
}
