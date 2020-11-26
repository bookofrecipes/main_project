package ru.geekbrains.bookofrecipes.service.functional

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L) : Either<L, Nothing>()

    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

    companion object {
        fun <L, R> left(value: L): Either<L, R> = Left(value)
        fun <L, R> right(value: R): Either<L, R> = Right(value)
    }
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>) =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }
