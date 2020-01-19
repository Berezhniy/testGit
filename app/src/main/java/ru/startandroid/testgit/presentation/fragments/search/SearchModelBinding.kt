package ru.startandroid.testgit.presentation.fragments.search

import ru.startandroid.testgit.presentation.activities.main.MainListener

class SearchModelBinding(
    private val searchModel: SearchModel,
    private val listener: MainListener
) {
    fun onSearchClick() {
        listener.onSearchClicked(searchModel)
    }
}