package ru.geekbrains.bookofrecipes.domain.use_cases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.geekbrains.bookofrecipes.service.Failure
import ru.geekbrains.bookofrecipes.service.functional.Either

abstract class UseCase<out Type, in Params> where Type : Any? {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}