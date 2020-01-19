package ru.startandroid.testgit.presentation.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.startandroid.testgit.App
import ru.startandroid.testgit.data.card_models.RepositoryDisplayModel
import ru.startandroid.testgit.di.component.ViewModelComponent
import ru.startandroid.testgit.presentation.adapter.DiffCallbackBaseCardModel
import ru.startandroid.testgit.presentation.adapter.PagingAdapter
import ru.startandroid.testgit.utils.multistate.MultiStateView

abstract class BasePagingFragment<V : ViewDataBinding>: BaseFragment<V>(), ItemsLoadListener<PagedList<RepositoryDisplayModel>> {

    protected var pagingAdapter: PagingAdapter = PagingAdapter(DiffCallbackBaseCardModel())

    abstract fun injectDependency(component: ViewModelComponent)

    abstract fun initListView()

    abstract fun getListView(): RecyclerView

    abstract fun getRefreshView(): SwipeRefreshLayout

    abstract fun getStateView() : MultiStateView

    abstract fun initObserver()

    abstract fun removeObserver()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayProgress()
        initListView()
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    override fun onDestroyView() {
        removeObserver()
        super.onDestroyView()
    }

    private fun createDaggerDependencies() {
        activity?.apply { injectDependency((application as App).getViewModelComponent()) }
    }
}