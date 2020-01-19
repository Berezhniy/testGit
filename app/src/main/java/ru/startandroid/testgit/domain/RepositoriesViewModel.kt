package ru.startandroid.testgit.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import ru.startandroid.testgit.data.card_models.RepositoryDisplayModel
import ru.startandroid.testgit.presentation.base.ItemsLoadListener
import ru.startandroid.testgit.usecases.RepositoriesUseCases
import ru.startandroid.testgit.utils.ConverterFactory
import java.util.NoSuchElementException

class RepositoriesViewModel(private val repositoriesUseCases: RepositoriesUseCases) : BasePagingViewModel() {

    init {
        initPagedConfig()
    }

    fun initLiveData(listener: ItemsLoadListener<PagedList<RepositoryDisplayModel>>) {
        itemLoadedListener = listener
        initPagedListLiveData(repositoriesUseCases.getCardsFactory(ConverterFactory()))
    }

    fun getPagedList(): LiveData<PagedList<RepositoryDisplayModel>> = listCards

    override fun fetchData(username: String) {
        compositeDisposable.add(repositoriesUseCases.fetchRepositories(username)
            .subscribe({ setRefreshing(false) }, { throwable ->
                if (throwable is NoSuchElementException) {
                    itemLoadedListener.onItemsLoaded(null)
                } else {
                    throwable.message?.let { itemLoadedListener.onLoadError(it) }
                }
                setRefreshing(false)
            })
        )
    }

    override fun rangeData(username: String, page: Int) {
        setLoading(true)
        compositeDisposable.add(repositoriesUseCases.fetchRepositoriesNext(username, page)
            .subscribe({ setLoading(false) },
                { setLoading(false) }
            )
        )
    }

    override fun clearCachedItems() {
        repositoriesUseCases.deleteCachedFeed()
    }
}
