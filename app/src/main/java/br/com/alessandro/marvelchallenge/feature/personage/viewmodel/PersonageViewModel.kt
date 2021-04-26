package br.com.alessandro.marvelchallenge.feature.personage.viewmodel

import androidx.lifecycle.LiveData
import br.com.alessandro.marvelchallenge.BaseViewModel
import br.com.alessandro.marvelchallenge.R
import br.com.alessandro.marvelchallenge.feature.personage.viewstate.PersonageViewState
import br.com.alessandro.marvelchallenge.interactor.personage.GetPersonagesInteractor
import br.com.alessandro.marvelchallenge.util.LiveEvent
import kotlinx.coroutines.launch
import java.lang.Exception

class PersonageViewModel(
    private val getPersonagesInteractor: GetPersonagesInteractor
): BaseViewModel() {

    private val state = LiveEvent<PersonageViewState>()
    val viewState: LiveData<PersonageViewState> = state

    private var sizeListPersonages = LIMITE_PERSONAGES

    /*TODO, refatorar */
    fun getPersonages() = launch {
        try {
            getPersonagesInteractor.execute(limit = LIMITE_PERSONAGES).apply {
                val size = this.data.results.size

                state.postValue(
                    PersonageViewState.ShowLists(
                        listCarousel = this.data.results.subList(0, 5).toMutableList(),
                        list = this.data.results.subList(5, size).toMutableList()
                    )
                )
            }
        } catch (error: Exception) {
            /*TODO, tratar melhor as excecoes */
            state.postValue(
                PersonageViewState.Error(message = R.string.message_error)
            )
        }
    }

    fun getMorePersonages() = launch {
        try {
            val offset = sizeListPersonages + 1
            sizeListPersonages += LIMITE_PERSONAGES

            getPersonagesInteractor.execute(offset, LIMITE_PERSONAGES).apply {
                state.postValue(
                    PersonageViewState.UpdateList(this.data.results)
                )
            }
        } catch (error: Exception) {
            /*TODO, tratar melhor as excecoes */
            state.postValue(
                PersonageViewState.Error(message = R.string.message_error_more_personages)
            )
        }
    }

    companion object {
        private const val LIMITE_PERSONAGES = 20
    }
}
