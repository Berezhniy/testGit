package ru.startandroid.testgit.usecases

import androidx.paging.DataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.startandroid.testgit.data.card_models.RepositoryDisplayModel
import ru.startandroid.testgit.usecases.repository.RepositoriesRepository
import ru.startandroid.testgit.utils.ConverterFactory

interface RepositoriesUseCases {

    fun fetchRepositories(username: String): Completable

    fun fetchRepositoriesNext(username: String, lastItemId: Int): Completable

    fun deleteCachedFeed(): Completable

    fun getCardsFactory(
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, RepositoryDisplayModel>
}

class RepositoriesUseCasesImpl(private val repository: RepositoriesRepository) : RepositoriesUseCases {

    override fun fetchRepositories(username: String): Completable =
        repository.fetchRepositories(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun fetchRepositoriesNext(username: String, lastItemId: Int): Completable =
        repository.fetchRepositoriesNext(username, lastItemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun deleteCachedFeed(): Completable =
        repository.deleteCachedRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    override fun getCardsFactory(
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, RepositoryDisplayModel> =
        repository.getFactory(modelConverter)
}