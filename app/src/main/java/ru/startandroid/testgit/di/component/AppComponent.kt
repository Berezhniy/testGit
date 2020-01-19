package ru.startandroid.testgit.di.component

import com.google.gson.Gson
import dagger.Component
import ru.startandroid.testgit.di.module.AppModule
import ru.startandroid.testgit.di.skope.AppScope

@AppScope//область видимости по AppScope
@Component(modules = [AppModule::class])//обслуживает AppModule
interface AppComponent {
    val gson: Gson//объект gson наследуется от Gson
}