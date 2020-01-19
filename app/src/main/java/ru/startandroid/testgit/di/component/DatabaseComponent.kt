package ru.startandroid.testgit.di.component

import dagger.Component
import ru.startandroid.testgit.di.module.DatabaseModule
import ru.startandroid.testgit.usecases.repository.data_source.RepositoryDataSource

@Component(modules = [DatabaseModule::class])//обслуживает DatabaseModule
interface DatabaseComponent {
    val repositoryDataSource: RepositoryDataSource//объект repositoryDataSource наследуется от RepositoryDataSource
}
