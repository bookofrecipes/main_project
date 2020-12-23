# Book of recipes
Приложение предназначено для поиска кулинарных рецептов по ингредиентам, либо получение случайного набора, с возможностью сохранения в избранное.
## API
В качестве backend'а используется [Spoonacular API](https://spoonacular.com/food-api/docs)
## Architecture
Приложение спроектировано в соответствии с принципами чистой архитектуры и состоит из трех функциональных блоков, вынесенных в отдельные пакеты:
![](https://github.com/bookofrecipes/main_project/blob/master/images/clean_architecture_reloaded_layers.png)
* Data: работа с источниками данных (БД, сеть).
![](https://github.com/bookofrecipes/main_project/blob/master/images/clean_archictecture_reloaded_repository.png)
* Domain: предоставляет интерфейс репозитория для Data, и набора UseCase'ов для Presentation.
* Presentation: UI реализованный с помощью паттерна MVVM средствами Android Jetpack.
![](https://github.com/bookofrecipes/main_project/blob/master/images/clean_architecture_reloaded_mvvm_app.png)
## Stack
* [Kotlin](https://kotlinlang.org/) - язык программирования
* Библиотеки:
  * [Koin](https://insert-koin.io/) - библиотека DI
  * [Kotlin coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - библиотека для работы с асинхронным кодом
  * [Retrofit](https://square.github.io/retrofit/) - работа с сетью
  * [Room](https://developer.android.com/jetpack/androidx/releases/room) - работа с БД
  * [Glide](http://bumptech.github.io/glide/) - загрузка и кэширование изображений
  
