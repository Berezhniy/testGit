package ru.startandroid.testgit.data.card_models

import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity

class RepositoryDisplayModel(private var repositoryEntity : RepositoryEntity) {

    fun getRepositoryId(): String {
        return repositoryEntity.id.toString()
    }

    fun getBaseModel(): RepositoryEntity {
        return repositoryEntity
    }

}