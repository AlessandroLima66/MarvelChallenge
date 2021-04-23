package br.com.alessandro.marvelchallenge.interactor.personage

import org.koin.dsl.module

val personagesInteracotModule = module {
    single { GetPersonagesInteractor(apiService = get()) }
}
