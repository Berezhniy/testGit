package ru.startandroid.testgit.usecases.repository.remote_data_source.communicator

import android.util.Log
import io.reactivex.Single
import retrofit2.Response
import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity

const val ITEMS_PER_PAGE = 20//создаём константу

class ServerCommunicator(private val mService: ApiService) {//mService наследуется от ApiService
//fetchRepositories наследуется от Single
fun fetchRepositories(username: String, lastItemId: Int): Single<Response<List<RepositoryEntity>>> {//в конструкторе username типа String и lastItemId типа Int
    return mService.fetchRepositories(username, lastItemId, ITEMS_PER_PAGE)//возвращаем ошибку и записываем в лог подробности
        .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
}
}