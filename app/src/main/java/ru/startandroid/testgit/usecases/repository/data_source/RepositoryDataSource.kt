package ru.startandroid.testgit.usecases.repository.data_source

import androidx.paging.DataSource
import ru.startandroid.testgit.data.card_models.RepositoryDisplayModel
import ru.startandroid.testgit.usecases.repository.data_source.database.AppDatabase
import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity
import ru.startandroid.testgit.utils.ConverterFactory

interface RepositoryDataSource {

    fun getRepositoriesFactory(converterFactory: ConverterFactory)//converterFactory наследуется от ConverterFactory
            : DataSource.Factory<Int, RepositoryDisplayModel>//создание источника данных

    fun deleteCachedRepositories()

    fun insert(repositoryItems: List<RepositoryEntity>)//repositoryItems наследуется от List типа RepositoryEntity

    fun insertAndClearCache(repositoryItems: List<RepositoryEntity>)
}

class RepositoryDataSourceImpl(private val database: AppDatabase) : RepositoryDataSource {//RepositoryDataSourceImpl наследуется от RepositoryDataSource
//приватное поле database наследуется от AppDatabase
override fun getRepositoriesFactory(//getRepositoriesFactory наследуется от  DataSource.Factory с key Int и value RepositoryDisplayModel
    converterFactory: ConverterFactory//converterFactory наследуется от ConverterFactory
): DataSource.Factory<Int, RepositoryDisplayModel> {//фабрика для источника данных
    return database.repositoryDao().getDataSource()//возвращаем mapByPage
        .mapByPage(converterFactory::convert)//mapByPage - применяет функцию к каждому значению генерируемому источниками данных
}

    override fun deleteCachedRepositories(): Unit=//объект Unit - тип с одним значением/соответствует типу void java
        database.repositoryDao().deleteCachedItems()//выполняем метод из repositoryDao

    override fun insert(repositoryItems: List<RepositoryEntity>) : Unit=//выполняем метод из repositoryDao
        database.repositoryDao().insert(repositoryItems)//с repositoryItems наследуемым от List типа RepositoryEntity

    override fun insertAndClearCache(repositoryItems: List<RepositoryEntity>): Unit=
        database.repositoryDao().insertAndClearCache(repositoryItems)
}