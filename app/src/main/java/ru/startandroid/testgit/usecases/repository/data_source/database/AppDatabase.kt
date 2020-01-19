package ru.startandroid.testgit.usecases.repository.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.startandroid.testgit.usecases.repository.data_source.database.dao.RepositoryDao
import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity

//Database - помечается основной класс для работы с бд. Этот класс должен быть абстрактным и наследоваться от RoomDatabase
@Database(entities = [RepositoryEntity::class], version = 1)//указываем Entity и версию
abstract class AppDatabase : RoomDatabase() {//AppDatabase наследуется от RoomDatabase
abstract fun repositoryDao(): RepositoryDao//repositoryDao наследуется от RepositoryDao
}