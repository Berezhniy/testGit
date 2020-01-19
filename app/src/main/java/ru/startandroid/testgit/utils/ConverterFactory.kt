package ru.startandroid.testgit.utils

import ru.startandroid.testgit.data.BaseModel
import ru.startandroid.testgit.data.card_models.RepositoryDisplayModel
import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity
import java.util.ArrayList

class ConverterFactory {

    fun convert(list: List<BaseModel>): List<RepositoryDisplayModel> {

        val items = ArrayList<RepositoryDisplayModel>()

        list.forEach {
            convert(it)?.apply {
                items.add(this)
            }
        }
        return items
    }

    private fun convert(item: BaseModel): RepositoryDisplayModel? {
        if(item is RepositoryEntity && item.isRepository){
            return RepositoryDisplayModel(item)
        }
        return null
    }
}
