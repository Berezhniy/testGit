package ru.startandroid.testgit.di.module

import dagger.Module
import dagger.Provides
import ru.startandroid.testgit.usecases.repository.data_source.RepositoryDataSource
import ru.startandroid.testgit.usecases.repository.data_source.RepositoryDataSourceImpl
import ru.startandroid.testgit.usecases.repository.data_source.database.AppDatabase

@Module//аннотируются классы в которых реализованы зависимости
class DatabaseModule(private val appDatabase: AppDatabase) {//кладём в конструктор приватный объект appDatabase наследуемый от AppDatabase
@Provides//ставится на каждом методе который что-то создаёт
internal fun providesAppDatabase(): AppDatabase {
    return appDatabase
}

    @Provides
    internal fun providesFeedDataSource(appDatabase: AppDatabase): RepositoryDataSource {
        //internal fun - видимость распространена на весь модуль
        //наследуем providesFeedDataSource от RepositoryDataSource
        //кладём в конструктор appDatabase наследуемый от AppDatabase
        return RepositoryDataSourceImpl(appDatabase)//возвращаем RepositoryDataSourceImpl с appDatabase в конструкторе
    }
}