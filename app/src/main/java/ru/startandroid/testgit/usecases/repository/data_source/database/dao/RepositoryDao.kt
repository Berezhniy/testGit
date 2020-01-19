package ru.startandroid.testgit.usecases.repository.data_source.database.dao

import androidx.paging.DataSource
import androidx.room.*
import ru.startandroid.testgit.usecases.repository.data_source.database.entity.RepositoryEntity

@Dao//класс помеченный @Dao должен быть интерфейсом или абстрактным классом
//во время компиляции Room сгенерирует реализацию этого класса
interface RepositoryDao {
    //Query - помечает метод в Dao, как метод запроса
    //существует 3 типа запроса: SELECT, UPDATE и DELETE
    @Query("SELECT * FROM repositories")
    fun queryFeeds(): List<RepositoryEntity>//queryFeeds наследуется от List типа RepositoryEntity

    @Query("SELECT * FROM repositories WHERE id = :id")
    fun queryById(id: Int): RepositoryEntity//queryById с id типа Int наследуется от RepositoryEntity
    //Insert - помечает метод вставки. Реализация метода вставит его параметры в базу данных
    @Insert
    fun insertList(listEntities: List<RepositoryEntity>)//listEntities наследуется от List типа RepositoryEntity
    //onConflict - действие в случае конфликта
    //onConflict=OnConflictStrategy.REPLACE - старая запись будет заменена новой
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insert(listEntities: List<RepositoryEntity>)//listEntities наследуется от List типа RepositoryEntity

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun update(entity: RepositoryEntity)//entity нраследуется от RepositoryEntity
    //Update - обновляет объекты в бд по ключу
    @Update
    fun updateAll(listEntities: List<RepositoryEntity>)//listEntities наследуется от List типа RepositoryEntity
    //Delete - удаляет объекты
    @Delete
    fun delete(entity: RepositoryEntity)//entity наследуется от RepositoryEntity
    //Query - для запроса объектов из базы
    @Query("DELETE FROM repositories WHERE cached = 1")//выполняется запрос по cached
    fun deleteCachedItems()//выполняется метод deleteCachedItems

    @Query("SELECT * FROM repositories")
    fun getDataSource(): DataSource.Factory<Int, RepositoryEntity>//key - Int, value - RepositoryEntity

    @Query("DELETE FROM repositories WHERE cached = 1")//выполняется запрос по cached
    fun deleteAllCachedItems()

    @Query("DELETE FROM repositories WHERE screenType = :screenType")//выполняется запрос по screenType
    fun deleteAllItemsByType(screenType: String)//screenType наследуется от String

    @Query("DELETE FROM repositories WHERE username = :username")//выполняется запрос по username
    fun deleteAllItemsByUsername(username: String)//username наследуется от String
    //позволяет выполнять несколько методов в рамках одной транзакции
    @Transaction//позволяет выполнять несколько методов в рамках одной транзакции
    fun insertAndClearCache(
        listEntities: List<RepositoryEntity>//listEntities наследуется от List типа RepositoryEntity
    ) {
        listEntities.forEach { it.username.let { deleteAllItemsByUsername(it) } }//удаляет все элементы по username
        insert(listEntities)//вставляем listEntities
    }
}