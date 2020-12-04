package ru.geekbrains.bookofrecipes.service.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.geekbrains.bookofrecipes.service.Failure

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeData(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> LifecycleOwner.observeFailure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))