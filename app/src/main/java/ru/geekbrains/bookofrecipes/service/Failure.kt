package ru.geekbrains.bookofrecipes.service

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    abstract class AppFailure : Failure()
}