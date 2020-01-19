package ru.startandroid.testgit.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.startandroid.testgit.App
import ru.startandroid.testgit.di.skope.ViewModelScope
import ru.startandroid.testgit.domain.RepositoriesViewModel
import ru.startandroid.testgit.usecases.RepositoriesUseCases

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesFeedViewModel(repositoriesUseCases: RepositoriesUseCases): RepositoriesViewModel {
        return RepositoriesViewModel(repositoriesUseCases)
    }
}