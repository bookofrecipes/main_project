package ru.geekbrains.bookofrecipes.domain

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    abstract class AppFailure : Failure()
}