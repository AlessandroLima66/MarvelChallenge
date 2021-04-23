package br.com.alessandro.marvelchallenge.data

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory {
       Client().createClient(currentTime = System.currentTimeMillis())
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}
