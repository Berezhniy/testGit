package ru.startandroid.testgit.di.component

import dagger.Component
import ru.startandroid.testgit.di.module.InteractorModule
import ru.startandroid.testgit.di.skope.InteractorScope
import ru.startandroid.testgit.usecases.RepositoriesUseCases

@InteractorScope//область видимости по InteractorScope
@Component(modules = [InteractorModule::class], dependencies = [RepositoryComponent::class])
//обслуживает InteractorModule
//зависит от RepositoryComponent
interface InteractorComponent {
    val repositoriesUseCases: RepositoriesUseCases//объект repositoriesUseCases наследуется от RepositoriesUseCases
}