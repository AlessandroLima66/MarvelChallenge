package br.com.alessandro.marvelchallenge

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel(), CoroutineScope {
    private val viewModelJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelJob.job

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }
}
