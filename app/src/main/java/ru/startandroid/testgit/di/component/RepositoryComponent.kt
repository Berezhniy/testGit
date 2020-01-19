package ru.startandroid.testgit.di.component

import dagger.Component
import ru.startandroid.testgit.di.module.RepositoryModule
import ru.startandroid.testgit.di.skope.RepositoryScope
import ru.startandroid.testgit.usecases.repository.RepositoriesRepository

@RepositoryScope//область видимости по RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
//обслуживает RepositoryModule
//зависит от ApiComponent и DatabaseComponent
interface RepositoryComponent {
    val repositoriesRepository: RepositoriesRepository//объект repositoriesRepository наследуется от RepositoriesRepository
}