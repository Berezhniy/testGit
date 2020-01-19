package ru.startandroid.testgit.presentation.fragments.repositories

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.startandroid.testgit.R
import ru.startandroid.testgit.data.card_models.RepositoryDisplayModel
import ru.startandroid.testgit.databinding.RepositoriesDataBinding
import ru.startandroid.testgit.di.component.ViewModelComponent
import ru.startandroid.testgit.domain.RepositoriesViewModel
import ru.startandroid.testgit.presentation.activities.main.EXTRAS_USERNAME
import ru.startandroid.testgit.presentation.activities.main.MainActivity
import ru.startandroid.testgit.presentation.base.BasePagingFragment
import ru.startandroid.testgit.utils.FIRST_LIST_POSITION
import ru.startandroid.testgit.utils.MIN_LIST_SIZE
import ru.startandroid.testgit.utils.RepositoriesLayoutManager
import ru.startandroid.testgit.utils.extention.showSnack
import ru.startandroid.testgit.utils.multistate.MultiStateView
import javax.inject.Inject

class RepositoriesFragment : BasePagingFragment<RepositoriesDataBinding>() {

    var viewModel: RepositoriesViewModel?=null
        @Inject set

    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(EXTRAS_USERNAME)?.let { username = it }
        setupAppBar()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int= R.layout.fragment_repositories

    override fun getListView(): RecyclerView =viewBinder.multiStateView.listView

    override fun getRefreshView(): SwipeRefreshLayout =viewBinder.swipeRefresh

    override fun getStateView(): MultiStateView =viewBinder.multiStateView.multiState

    override fun initListView() {
        context?.apply {
            val layoutManager = RepositoriesLayoutManager(this)

            getListView().layoutManager = layoutManager
            getListView().adapter = pagingAdapter

            listener?.let {
                pagingAdapter.listener = it
            }

            pagingAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    if (positionStart == FIRST_LIST_POSITION && itemCount == MIN_LIST_SIZE) {
                        getListView().scrollToPosition(FIRST_LIST_POSITION)
                    }
                }
            })

            viewModel?.getRefreshing().let { viewBinder.setVariable(BR.refreshing, it) }

            viewModel?.isLoading().let { viewBinder.setVariable(BR.loading, it) }
        }
    }

    override fun initObserver() {
        viewModel?.initLiveData(this)
        viewModel?.getPagedList()?.observe(this, Observer(this@RepositoriesFragment::onItemsLoaded))
    }

    override fun removeObserver() {
        viewModel?.getPagedList()?.removeObservers(this)
    }

    override fun setupViewLogic(binding: RepositoriesDataBinding) {
        viewModel?.fetchData(username)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel?.setRefreshing(true)
            viewModel?.fetchData(username)
        }
    }

    override fun onItemsLoaded(items: PagedList<RepositoryDisplayModel>?) {
        if (items.isNullOrEmpty()) {
            getStateView().viewState=MultiStateView.VIEW_STATE_EMPTY
        } else {
            pagingAdapter.submitList(items)
            getStateView().viewState=MultiStateView.VIEW_STATE_CONTENT
        }
    }

    override fun displayProgress() {
        getStateView().viewState=MultiStateView.VIEW_STATE_LOADING
    }

    override fun onLoadError(errorMessage: String) {
        getStateView().viewState=MultiStateView.VIEW_STATE_ERROR
        activity?.showSnack(errorMessage)
    }

    override fun onDetach() {
        viewModel?.clearCachedItems()
        super.onDetach()
    }

    private fun setupAppBar() = (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.title_repository_screen, username)
    }

    companion object {
        @JvmStatic
        fun newInstance(query: String): RepositoriesFragment = RepositoriesFragment().apply {
            arguments = Bundle().apply { putString(EXTRAS_USERNAME, query) }
        }
    }
}