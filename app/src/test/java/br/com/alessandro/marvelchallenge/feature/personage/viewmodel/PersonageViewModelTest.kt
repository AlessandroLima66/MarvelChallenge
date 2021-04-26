package br.com.alessandro.marvelchallenge.feature.personage.viewmodel

import br.com.alessandro.marvelchallenge.data.model.Character
import br.com.alessandro.marvelchallenge.data.model.Thumbnail
import br.com.alessandro.marvelchallenge.feature.personage.util.instantLiveDataAndCoroutineRules
import br.com.alessandro.marvelchallenge.feature.personage.viewstate.PersonageViewState
import br.com.alessandro.marvelchallenge.interactor.personage.GetPersonagesInteractor
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class PersonageViewModelTest {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private val getPersonagesInteractor: GetPersonagesInteractor = mockk()
    private val viewModel = PersonageViewModel(getPersonagesInteractor)

    private val listaComUmPersonagem = listOf(
        Character(
            id = 0,
            thumbnail = Thumbnail(path = "testes", extension = "jpg"),
            name = "teste",
            description = "teste",
            modified = ""
        )
    )

    private val listaComSeisPersonagens: List<Character> = listOf(
        Character(
            id = 0,
            thumbnail = Thumbnail(path = "testes", extension = "jpg"),
            name = "teste",
            description = "teste",
            modified = ""
        ),
        Character(
            id = 1,
            thumbnail = Thumbnail(path = "testes", extension = "jpg"),
            name = "teste",
            description = "teste",
            modified = ""
        ),
        Character(
            id = 2,
            thumbnail = Thumbnail(path = "testes", extension = "jpg"),
            name = "teste",
            description = "teste",
            modified = ""
        ),
        Character(
            id = 3,
            thumbnail = Thumbnail(path = "testes", extension = "jpg"),
            name = "teste",
            description = "teste",
            modified = ""
        ),
        Character(
            id = 4,
            thumbnail = Thumbnail(path = "testes", extension = "jpg"),
            name = "teste",
            description = "teste",
            modified = ""
        ),
        Character(
            id = 5,
            thumbnail = Thumbnail(path = "testes", extension = "jpg"),
            name = "teste",
            description = "teste",
            modified = ""
        )
    )

    @Test
    fun getPersonages_RetornaListaComMaisDeCincoPersonagens_StatusShowLists() = runBlocking {
        coEvery {
            getPersonagesInteractor.execute(
                offset = any(),
                limit = any()
            )
        } returns listaComSeisPersonagens

        viewModel.getPersonages()

        val state = viewModel.viewState.value as PersonageViewState.ShowLists
        assertEquals(1, state.list.size)
        assertEquals(5, state.listCarousel.size)
    }

    @Test
    fun getPersonages_RetornaListaComMenosDeCincoPersonagens_StatusError() = runBlocking {

        coEvery {
            getPersonagesInteractor.execute(
                offset = any(),
                limit = any()
            )
        } returns listaComUmPersonagem

        viewModel.getPersonages()

        assertTrue(viewModel.viewState.value is PersonageViewState.Error)
    }

    @Test
    fun getPersonages_RetornaExecao_StatusError() = runBlocking {
        coEvery {
            getPersonagesInteractor.execute(
                offset = any(),
                limit = any()
            )
        } throws Exception()

        viewModel.getPersonages()

        assertTrue(viewModel.viewState.value is PersonageViewState.Error)
    }

    @Test
    fun getMorePersonages_RetornaListaComPersonagens_StatusUpdateList() = runBlocking {
        coEvery {
            getPersonagesInteractor.execute(
                offset = any(),
                limit = any()
            )
        } returns listaComSeisPersonagens

        viewModel.getMorePersonages()

        assertTrue(viewModel.viewState.value is PersonageViewState.UpdateList)
    }

    @Test
    fun getMorePersonages_RetornaException_StatusError() = runBlocking {
        coEvery {
            getPersonagesInteractor.execute(
                offset = any(),
                limit = any()
            )
        } throws Exception()

        viewModel.getMorePersonages()

        assertTrue(viewModel.viewState.value is PersonageViewState.Error)
    }
}
