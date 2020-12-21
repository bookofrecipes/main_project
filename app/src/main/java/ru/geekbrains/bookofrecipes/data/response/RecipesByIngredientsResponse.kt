package ru.geekbrains.bookofrecipes.data.response

class RecipesByIngredientsResponse : ArrayList<RecipesByIngredientsResponseItem>() {
    companion object {
        val empty = RecipesByIngredientsResponse()
    }
}