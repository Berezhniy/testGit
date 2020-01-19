package ru.startandroid.testgit.di.skope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope//аннотация позволяющая создавать локальные и глобальные экземпляры(единые) данной зависимости
@Retention(RetentionPolicy.CLASS)//аннотация будет записана в class-файл, но не должна быть доступна во время выполнения(runtime)
annotation class//для объявления аннотация
InteractorScope