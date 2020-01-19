package ru.startandroid.testgit.di.component

import dagger.Component
import ru.startandroid.testgit.di.module.ApiModule
import ru.startandroid.testgit.di.skope.ApiScope
import ru.startandroid.testgit.usecases.repository.remote_data_source.RepositoryRemoteDataSource

@ApiScope//область видимости по ApiScope
@Component(modules = [ApiModule::class], dependencies = [AppComponent::class])
//обслуживает ApiModule
//зависит от AppComponent
interface ApiComponent {
    val repositoryRemoteDataSource: RepositoryRemoteDataSource
    //объект repositoryRemoteDataSource наследуется от RepositoryRemoteDataSource
}
