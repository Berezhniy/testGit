package ru.startandroid.testgit.di.module

import android.app.Application
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ru.startandroid.testgit.di.skope.AppScope

@Module// аннотируются классы в которых реализованы зависимости
class  AppModule(private val app : Application) {//кладём в конструктор приватный объект app наследуемый от Application

    @Provides//ставится на каждом методе который что-то создаёт
    @AppScope//область видимости по AppScope
    fun provideApplication(): Application {//наследуем provideApplication от Application
        return app//возвращаем app
    }

    @Provides//ставится на каждом методе который что-то создаёт
    @AppScope//область видимости по AppScope
    fun provideGson(): Gson {//наследуем provideGson от Gson
        return Gson()//возвращаем Gson
    }
}