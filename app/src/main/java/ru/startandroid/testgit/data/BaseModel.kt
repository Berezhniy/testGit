package ru.startandroid.testgit.data

import androidx.databinding.BaseObservable

abstract class BaseModel: BaseObservable() {
    abstract fun convertItemForDataSource(): BaseModel
}
