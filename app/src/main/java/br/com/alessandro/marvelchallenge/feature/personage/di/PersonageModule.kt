package br.com.alessandro.marvelchallenge.feature.personage.di

import br.com.alessandro.marvelchallenge.feature.personage.viewmodel.PersonageViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val personageModule = module {
    viewModel { PersonageViewModel( getPersonagesInteractor = get()) }
}
