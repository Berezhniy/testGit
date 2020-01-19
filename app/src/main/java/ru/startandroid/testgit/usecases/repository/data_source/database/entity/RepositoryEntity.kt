package ru.startandroid.testgit.usecases.repository.data_source.database.entity

import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.startandroid.testgit.data.BaseModel
import ru.startandroid.testgit.utils.CACHED_VALUE
import ru.startandroid.testgit.utils.DEFAULT_CACHE_VALUE
import ru.startandroid.testgit.utils.DEFAULT_SCREEN
import ru.startandroid.testgit.utils.extention.parseDate
import ru.startandroid.testgit.utils.extention.parseTime

const val URL_SUFFIX_COMMITS = "/commits" //создаём константу

@Entity(tableName="repositories")//указывает что данный класс является сущностью
data class RepositoryEntity(//указываем что это класс данных

    @PrimaryKey(autoGenerate=false)//первичный ключ для назначения какого-либо поля ключем
    //autoGenerate - выключен, база данных не будет сама генерировать значение
    //сериализация - процесс, который переводит объект в последовательность байтов, по которой его можно полностью восстановить
    @SerializedName("id")//аннотация, указывающая, что это поле может быть сериализировано/десериализировано в json
    //с указанным значением имени в качестве имени поля
    var id: Int,//это id представляется как id в json

    @SerializedName("node_id")
    var nodeId: String,//это nodeId представляется как node_id в json

    @SerializedName("name")
    var name: String,//это nodeId представляется как node_id в json

    @SerializedName("description")
    var description: String?,//это description представляется как description в json

    @SerializedName("language")
    var language: String?,//это language представляется как language в json

    @SerializedName("private")
    var private: Boolean,//это private представляется как private в json

    @SerializedName("html_url")
    var htmlUrl: String,//это htmlUrl представляется как html_url в json

    @SerializedName("tags_url")
    var tagsUrl: String,//это tagsUrl представляется как tags_url в json

    @SerializedName("created_at")
    @Bindable//Bindable применяется к любому методу-получателю доступа класса Observable
    var createdAt: String,//это createdAt представляется как created_at в json

    @SerializedName("updated_at")
    var updatedAt: String,//это updatedAt представляется как updated_at в json

    @SerializedName("pushed_at")
    var pushedAt: String,//это pushedAt представляется как pushed_at в json

    @SerializedName("forks")
    var forks: Int,//это forks представляется как forks в json

    @SerializedName("default_branch")
    var defaultBranch: String,

    var screenType: String?=DEFAULT_SCREEN,

    var username: String,

    var cached: Int?=DEFAULT_CACHE_VALUE
) : BaseModel() {//RepositoryEntity наследуется от BaseModel

    override fun convertItemForDataSource(): RepositoryEntity {//convertItemForDataSource наслудуется от RepositoryEntity
        return RepositoryEntity(//возвращаем RepositoryEntity с параметрами внутри конструктора
            id,
            nodeId,
            name,
            description,
            language,
            private,
            htmlUrl,
            tagsUrl,
            createdAt,
            updatedAt,
            pushedAt,
            forks,
            defaultBranch,
            screenType,
            username,
            cached
        )
    }

    constructor() : this(//constructor наследуется от this со всеми праметрами
        0,
        "",
        "",
        "",
        "",
        false,
        "",
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        DEFAULT_CACHE_VALUE
    )

    val isRepository: Boolean//isRepository типа Boolean
    get()=true//гет выполняется

    fun getRepoId(): String=id.toString()//достаём из id строку


    fun getParsedCreatedAtDate(): String=createdAt.parseDate()//парсим дату

    fun getParsedCreatedAtTime(): String=createdAt.parseTime()//парсим время

    fun getCommitsUrl(): String="$htmlUrl$URL_SUFFIX_COMMITS"//кладём в строку htmlUrl и URL_SUFFIX_COMMITS

    fun convertItemForDataSource(item: RepositoryEntity, isCached: Boolean?): RepositoryEntity {//convertItemForDataSource наследуется от RepositoryEntity
        isCached?.let { item.cached=CACHED_VALUE }//вызываем функция со значением this
        //cached айтема равен CACHED_VALUE
        return item//возвращаем item
    }
}