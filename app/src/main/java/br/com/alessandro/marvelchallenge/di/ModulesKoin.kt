package br.com.alessandro.marvelchallenge.di

import br.com.alessandro.marvelchallenge.data.dataModule
import br.com.alessandro.marvelchallenge.feature.personage.di.personageModule
import br.com.alessandro.marvelchallenge.interactor.personage.personagesInteracotModule
import br.com.alessandro.marvelchallenge.picasso.picassoModule

val appModules = listOf(
    dataModule,
    picassoModule
)

val featureModule = listOf(
    personageModule
)

val interactorModule = listOf(
    personagesInteracotModule
)
