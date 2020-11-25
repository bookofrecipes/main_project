package ru.geekbrains.bookofrecipes.domain.use_cases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(params: Params, onResult: (Type) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) {
            run(params)
        }

        GlobalScope.launch(Dispatchers.Main) {
            onResult(job.await())
        }
    }
}