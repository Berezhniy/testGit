package ru.startandroid.testgit.di.module

import dagger.Module
import dagger.Provides
import ru.startandroid.testgit.di.skope.RepositoryScope
import ru.startandroid.testgit.usecases.repository.RepositoriesRepository
import ru.startandroid.testgit.usecases.repository.RepositoriesRepositoryImpl
import ru.startandroid.testgit.usecases.repository.data_source.RepositoryDataSource
import ru.startandroid.testgit.usecases.repository.remote_data_source.RepositoryRemoteDataSource

@Module//аннотируются классы в которых реализованы зависимости
class RepositoryModule {

    @RepositoryScope//область видимости по RepositoryScope
    @Provides//ставится на каждом методе который что-то создаёт
    internal fun providesFeedRepository(repositoryRemoteDataSource: RepositoryRemoteDataSource, repositoryDataSource: RepositoryDataSource): RepositoriesRepository {
        //internal fun - видимость распространена на весь модуль
        //наследуем providesFeedRepository от RepositoriesRepository
        //кладём в конструктор repositoryRemoteDataSource наследуемый от RepositoryRemoteDataSource
        //и repositoryDataSource наследуемый от RepositoryDataSource
        return RepositoriesRepositoryImpl(repositoryRemoteDataSource, repositoryDataSource)
        //возвращаем RepositoriesRepositoryImpl с repositoryRemoteDataSource и repositoryDataSource в конструкторе
    }
}