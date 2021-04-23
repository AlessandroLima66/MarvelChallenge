package br.com.alessandro.marvelchallenge

import android.app.Application
import br.com.alessandro.marvelchallenge.di.appModules
import br.com.alessandro.marvelchallenge.di.featureModule
import br.com.alessandro.marvelchallenge.di.interactorModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelChallengeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelChallengeApplication)
            modules(appModules + featureModule + interactorModule)
        }
    }
}
