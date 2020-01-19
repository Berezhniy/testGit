package ru.startandroid.testgit.presentation.fragments.search

import ru.startandroid.testgit.R
import ru.startandroid.testgit.databinding.SearchDataBinding
import ru.startandroid.testgit.presentation.activities.main.MainActivity
import ru.startandroid.testgit.presentation.base.BaseFragment
import ru.startandroid.testgit.utils.extention.hideKeyboardWithClearFocus

class SearchFragment : BaseFragment<SearchDataBinding>(), SearchCallback {

    private lateinit var searchModelBinding: SearchModelBinding

    private val searchModel = SearchModel()

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun setupViewLogic(binder: SearchDataBinding) {
        searchModelBinding = SearchModelBinding(searchModel, (requireActivity() as MainActivity))
        binder.bindingModel = searchModelBinding
        binder.etUserName.addTextChangedListener(SearchTextWatcher(this, searchModel, binder.etUserName))

        binder.btnSearch.setOnClickListener {
            it.hideKeyboardWithClearFocus()
            searchModelBinding.onSearchClick()
        }
        setupAppBar()
    }

    override fun enableSearchButton(flag: Boolean) {
        viewBinder.btnSearch.isEnabled = flag
    }

    private fun setupAppBar() = (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(false)
        title = getString(R.string.app_title_search)
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}
