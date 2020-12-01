package ru.geekbrains.bookofrecipes.data

sealed class DataSourceType {
    object LocalDataSource : DataSourceType()
    object RemoteDataSource : DataSourceType()
}