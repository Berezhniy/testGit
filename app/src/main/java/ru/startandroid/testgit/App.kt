package ru.startandroid.testgit

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.room.Room
import ru.startandroid.testgit.di.component.*
import ru.startandroid.testgit.di.module.*
import ru.startandroid.testgit.usecases.repository.data_source.database.AppDatabase
import ru.startandroid.testgit.utils.DATABASE_NAME

class App: MultiDexApplication() {//класс App наследуется от MultiDexApplication
//приватные объекты с отложенной инициализацией
private lateinit var viewModelComponent: ViewModelComponent//viewModelComponent наследуется от ViewModelComponent
    private lateinit var database: AppDatabase//database наследуется от AppDatabase

    init {//блок инициализатора
        applicationInstance = this
    }

    companion object {//вспомогательный объект
    private lateinit var applicationInstance: App//приватное поле с отложенной инициализацией
        //applicationInstance наследуется от App

        fun get(): App {//наследуется от App
            return applicationInstance.applicationContext as App//applicationInstance.applicationContext типа App
            //возвращаем applicationInstance.applicationContext
        }
    }
    //может быть переопределена
    override fun attachBaseContext(base: Context?) {//установка базового контекста
        //base наследуется от Context
        super.attachBaseContext(base)//базовый контекст - base
        MultiDex.install(this)//подгружаем дополнительные .dex файлы
    }
    //может быть переопределена
    override fun onCreate() {
        super.onCreate()//создание
        initRoom()//добавляем Room
        initDagger()//добавляем Dagger
    }

    private fun initRoom() {//приватная функция добавления Room
        database = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME)//создаём RoomDatabase
            .allowMainThreadQueries()//В этом случае вы не будем получать Exception при работе в UI потоке
            .build()//сборка
    }

    private fun initDagger() {//приватная функция добавления Dagger
        val appComponent = DaggerAppComponent.builder()//инициализация DaggerAppComponent
            .appModule(AppModule(this))//настройка зависимостей
            .build()

        val apiComponent = DaggerApiComponent.builder()//инициализация DaggerApiComponent
            .appComponent(appComponent)//настройка зависимостей
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()//инициализация DaggerDatabaseComponent
            .databaseModule(DatabaseModule(database))//настройка зависимостей
            .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()//инициализация DaggerRepositoryComponent
            .apiComponent(apiComponent)//настройка зависимостей
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()

        val interactorComponent = DaggerInteractorComponent.builder()//инициализация DaggerInteractorComponent
            .repositoryComponent(repositoryComponent)//настройка зависимостей
            .interactorModule(InteractorModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()//инициализация DaggerViewModelComponent
            .interactorComponent(interactorComponent)//настройка зависимостей
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    fun getViewModelComponent(): ViewModelComponent {//getViewModelComponent наследуется от ViewModelComponent
        return this.viewModelComponent//возвращаем viewModelComponent
    }
}