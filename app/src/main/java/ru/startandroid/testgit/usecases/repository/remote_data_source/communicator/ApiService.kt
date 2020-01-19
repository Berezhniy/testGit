package ru.startandroid.testgit.usecases.repository.remote_data_source.communicator

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity

interface ApiService {

    @GET("/users/{user}/repos")//get-запрос с параметрами
    fun fetchRepositories(
        @Path("user") user: String,//user подставится в {user} типом String
        @Query("page") lastPage: Int,//выставляем запросы с параметрами
        @Query("per_page") perPage: Int
    ): Single<Response<List<RepositoryEntity>>>
}
