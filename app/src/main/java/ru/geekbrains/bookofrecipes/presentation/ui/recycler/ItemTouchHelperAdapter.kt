package ru.geekbrains.bookofrecipes.presentation.ui.recycler

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition : Int, toPosition : Int): Boolean
    fun onItemDismiss(position : Int)
}