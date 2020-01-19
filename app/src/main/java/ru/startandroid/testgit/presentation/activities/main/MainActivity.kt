package ru.startandroid.testgit.presentation.activities.main


import android.view.MenuItem
import ru.startandroid.testgit.R
import ru.startandroid.testgit.databinding.MainDataBinding
import ru.startandroid.testgit.di.component.ViewModelComponent
import ru.startandroid.testgit.presentation.base.BaseActivity
import ru.startandroid.testgit.presentation.fragments.repositories.RepositoriesFragment
import ru.startandroid.testgit.presentation.fragments.search.SearchFragment
import ru.startandroid.testgit.presentation.fragments.search.SearchModel
import ru.startandroid.testgit.utils.extention.openWebViewPage
import ru.startandroid.testgit.utils.extention.replaceFragment

const val EXTRAS_USERNAME = "EXTRAS_USERNAME"

class MainActivity : BaseActivity<MainDataBinding>(),
    MainListener {

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setupViewLogic(binding: MainDataBinding) {
        openSearchFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {

                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRepositoryClicked(url: String) {
        openWebViewPage(url)
    }

    override fun onSearchClicked(searchModel: SearchModel) {
        openRepositoriesFragment(searchModel.query)
    }

    override fun openRepositoriesFragment(query : String) {
        this.replaceFragment(R.id.mainContent, RepositoriesFragment.newInstance(query), true, false)
    }

    private fun openSearchFragment() {
        this.replaceFragment(R.id.mainContent, SearchFragment.newInstance(), false, false)
    }
}
