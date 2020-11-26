package ru.geekbrains.bookofrecipes.service

import android.app.Application
import org.koin.core.context.startKoin
import ru.geekbrains.bookofrecipes.service.di.module.appModule
import ru.geekbrains.bookofrecipes.service.di.module.repoModule

class RecipesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(appModule, repoModule))
        }
    }
}