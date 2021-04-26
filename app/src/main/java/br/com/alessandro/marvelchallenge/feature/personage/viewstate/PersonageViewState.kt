package br.com.alessandro.marvelchallenge.feature.personage.viewstate

import br.com.alessandro.marvelchallenge.data.model.Character

sealed class PersonageViewState {
    data class ShowLists(
        val list: MutableList<Character>,
        val listCarousel: MutableList<Character>
    ): PersonageViewState()

    data class UpdateList(
        val list: List<Character>
    ): PersonageViewState()
    
    data class Error(
        val message: Int
    ) : PersonageViewState()
}
