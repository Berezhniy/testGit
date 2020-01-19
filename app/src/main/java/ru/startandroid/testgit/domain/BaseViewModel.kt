package ru.startandroid.testgit.domain

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.startandroid.testgit.presentation.base.LoadingState

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable= CompositeDisposable()
    val macroLoadingState = MediatorLiveData<LoadingState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}