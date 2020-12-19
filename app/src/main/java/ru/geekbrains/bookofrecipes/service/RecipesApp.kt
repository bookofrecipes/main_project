package ru.geekbrains.bookofrecipes.service

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.geekbrains.bookofrecipes.service.di.module.appModule
import ru.geekbrains.bookofrecipes.service.di.module.databaseModule
import ru.geekbrains.bookofrecipes.service.di.module.repoModule
import ru.geekbrains.bookofrecipes.service.di.module.retrofitModule

class RecipesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RecipesApp)
            modules(listOf(appModule, repoModule, retrofitModule, databaseModule))
        }
    }
}