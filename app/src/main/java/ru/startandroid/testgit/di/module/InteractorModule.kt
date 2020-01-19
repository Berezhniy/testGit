package ru.startandroid.testgit.di.module

import dagger.Module
import dagger.Provides
import ru.startandroid.testgit.di.skope.InteractorScope
import ru.startandroid.testgit.usecases.RepositoriesUseCases
import ru.startandroid.testgit.usecases.RepositoriesUseCasesImpl
import ru.startandroid.testgit.usecases.repository.RepositoriesRepository

@Module//аннотируются классы в которых реализованы зависимости
class InteractorModule {

    @InteractorScope//область видимости по InteractorScope
    @Provides//ставится на каждом методе который что-то создаёт
    internal fun providesRepositoriesUseCases(repository: RepositoriesRepository): RepositoriesUseCases {
        //internal fun - видимость распространена на весь модуль
        //наследуем providesRepositoriesUseCases от RepositoriesUseCases
        //кладём в конструктор repository наследуемый от RepositoriesRepository
        return RepositoriesUseCasesImpl(repository)//возвращаем RepositoriesUseCasesImpl с repository в конструкторе
    }
}