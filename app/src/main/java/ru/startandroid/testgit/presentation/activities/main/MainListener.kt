package ru.startandroid.testgit.presentation.activities.main

import ru.startandroid.testgit.presentation.fragments.search.SearchModel

interface MainListener {
    fun onRepositoryClicked(url: String)
    fun onSearchClicked(searchModel: SearchModel)
    fun openRepositoriesFragment(query: String)
}