package ru.startandroid.testgit.usecases.repository.remote_data_source

import io.reactivex.Single
import retrofit2.Response
import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity
import ru.startandroid.testgit.usecases.repository.remote_data_source.communicator.ServerCommunicator

interface RepositoryRemoteDataSource {

    fun fetchRepositories(username: String): Single<Response<List<RepositoryEntity>>>//fetchRepositories наследуется от Single

    fun fetchRepositoriesNext(username: String, lastItemId: Int): Single<Response<List<RepositoryEntity>>>//fetchRepositoriesNext наследуется от Single
}
//RepositoryRemoteDataSourceImpl наследуется от RepositoryRemoteDataSource
class RepositoryRemoteDataSourceImpl(private val serverCommunicator: ServerCommunicator) : RepositoryRemoteDataSource {

    override fun fetchRepositories(username: String): Single<Response<List<RepositoryEntity>>> =
        serverCommunicator.fetchRepositories(username = username, lastItemId=1)//с serverCommunicator выполняется метод fetchRepositories с заданными данными

    //fetchRepositoriesNext наследуется от Single
    override fun fetchRepositoriesNext(username: String, lastItemId: Int): Single<Response<List<RepositoryEntity>>> =
        serverCommunicator.fetchRepositories(username = username, lastItemId=lastItemId)//serverCommunicator выполняется метод fetchRepositories с заданными параметрами
}