package ru.startandroid.testgit.di.component

import dagger.Component
import ru.startandroid.testgit.di.module.ViewModelModule
import ru.startandroid.testgit.di.skope.ViewModelScope
import ru.startandroid.testgit.presentation.activities.main.MainActivity
import ru.startandroid.testgit.presentation.activities.splash.SplashActivity
import ru.startandroid.testgit.presentation.fragments.repositories.RepositoriesFragment

@ViewModelScope//область видимости по ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
//обслуживает ViewModelModule
//зависит от InteractorComponent
interface ViewModelComponent {
    fun inject(activity: SplashActivity)//внедряем в SplashActivity
    fun inject(activity: MainActivity)//внедряем в MainActivity
    fun inject(fragment: RepositoriesFragment)//внедряем в RepositoriesFragment
}